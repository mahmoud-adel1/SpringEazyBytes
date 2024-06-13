package com.eazybytes.eazyschool.proxy;

import com.eazybytes.eazyschool.config.ProjectConfiguration;
import com.eazybytes.eazyschool.model.Contact;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "contact", url = "http://localhost:8080/api/contact",
        configuration = ProjectConfiguration.class)

public interface ContactProxy {
    @GetMapping(value = "/getMessagesByStatus")
    @Headers(value = {"Content-Type: Application/json", "InvocationFrom: FeignClient"})
    public List<Contact> getMessagesByStatus(@RequestParam(name = "Status") String status);
}
