package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Course;
import com.eazybytes.eazyschool.model.EazyClass;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.EazyClassRepository;
import com.eazybytes.eazyschool.service.AdminService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/displayClasses")
    public String displayClasses(Model model,
                                 @RequestParam(name = "error", required = false) String error) {
        String errorMessage=null;
        List<EazyClass> classes = adminService.getAllClasses();
        if(error!=null) {
//            String fieldError = (String) model.getAttribute("fieldError");
            errorMessage = "Errors: " + error;
            model.addAttribute("errorMessage",errorMessage);
        }
        model.addAttribute("eazyClasses",classes);
        model.addAttribute("eazyClass",new EazyClass());
        return "classes";
    }

    @PostMapping("/addNewClass")
    public String addNewClass(@Valid @ModelAttribute(name = "eazyClass") EazyClass eazyClass, Errors errors,Model model) {
        if(errors.hasErrors()) {
            String error = errors.toString();
//            model.addAttribute("fieldError",error);
//            return "redirect:/admin/displayClasses?error=true";
            List<EazyClass> classes = adminService.getAllClasses();
            model.addAttribute("eazyClasses",classes);
            return "classes";
        }
        adminService.createNewClass(eazyClass);
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/deleteClass")
    public String deleteClass(@RequestParam(name = "id") int classId) {
        Optional<EazyClass> eazyClass = adminService.findClass(classId);
        for(Person person : eazyClass.get().getPersons()) {
            person.setEazyClass(null);
        }
        adminService.deleteClass(eazyClass.get());
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/displayStudents")
    public String displayStudents(@RequestParam(name = "classId") int classId,
                                  Model model, HttpSession session,
                                  @RequestParam(name = "error", required = false) String error) {
        String errorMessage = null;
        Optional<EazyClass> eazyClass = adminService.findClass(classId);
        session.setAttribute("eazyClass",eazyClass.get());
        if(error != null) {
            errorMessage = "Invalid Email";
            model.addAttribute("errorMessage",errorMessage);
        }
        model.addAttribute("eazyClass",eazyClass.get());
        model.addAttribute("person",new Person());
        return "students";
    }

    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute(name = "person") Person person, HttpSession session) {
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Person student = adminService.findPersonByEmail(person.getEmail());
        if(student != null && student.getPersonId()>0) {
            eazyClass.getPersons().add(student);
            student.setEazyClass(eazyClass);
            adminService.updateClass(eazyClass);
//            session.setAttribute("eazyClass",eazyClass);
            return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
        }
        return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId()+"&error=true";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("personId") int personId, HttpSession session) {
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Optional<Person> person = adminService.findPersonById(personId);
        eazyClass.getPersons().remove(person.get());
        person.get().setEazyClass(null);
        adminService.updateClass(eazyClass);
//        session.setAttribute("eazyClass",eazyClass);
        return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
    }

    @GetMapping("/displayCourses")
    public String displayCourses(Model model) {
        List<Course> courses = adminService.getAllCourses();
        model.addAttribute("course",new Course());
        model.addAttribute("courses",courses);
        return "courses_secure";
    }

    @PostMapping("/addNewCourse")
    public String addNewCourse(@Valid @ModelAttribute(name = "course") Course course,Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("courses",adminService.getAllCourses());
            return "courses_secure";
        }
        adminService.createCourse(course);
        return "redirect:/admin/displayCourses";
    }

    @GetMapping("/viewStudents")
    public String viewStudents(@RequestParam(name = "courseId") int courseId, Model model,
                               HttpSession session,
                               @RequestParam(name = "error", required = false) String error) {
        String errorMessage = null;
        Optional<Course> course = adminService.getCourseById(courseId);
        model.addAttribute("course",course.get());
        model.addAttribute("person",new Person());
        session.setAttribute("course",course.get());
        if(error != null) {
            errorMessage = "Email Address Not Found";
            model.addAttribute("errorMessage",errorMessage);
        }
        return "course_students";
    }

    @PostMapping("/addStudentToCourse")
    public String addStudentToCourse(@ModelAttribute(name = "person") Person person,HttpSession session, Model model) {
        Course course = (Course) session.getAttribute("course");
        Person student = adminService.findPersonByEmail(person.getEmail());
        if(student == null || !(student.getPersonId()>0)) {
            return "redirect:/admin/viewStudents?courseId="+course.getCourseId()+"&error=true";
        }
        course.getPersons().add(person);
        student.getCourses().add(course);
        adminService.updatePerson(student);
        return "redirect:/admin/viewStudents?courseId="+course.getCourseId();
    }

    @GetMapping("/deleteStudentFromCourse")
    public String deleteStudentFromCourse(@RequestParam(name = "personId") int personId, HttpSession session) {
        Optional<Person> person = adminService.findPersonById(personId);
        Course course = (Course) session.getAttribute("course");
        if(person.isPresent()) {
            course.getPersons().remove(person);
            person.get().getCourses().remove(course);
            adminService.updatePerson(person.get());
        }
            return "redirect:/admin/viewStudents?courseId="+course.getCourseId();
    }
}
