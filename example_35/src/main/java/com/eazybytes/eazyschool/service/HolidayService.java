package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Holiday;
import com.eazybytes.eazyschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HolidayService {

    private HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> findAll() {
        Iterable<Holiday> holidayIterable = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport
                .stream(holidayIterable.spliterator(),false)
                .collect(Collectors.toList());
        return holidayList;
    }
}
