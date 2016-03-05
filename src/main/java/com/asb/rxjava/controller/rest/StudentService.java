package com.asb.rxjava.controller.rest;

import com.asb.rxjava.model.eo.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by arjun on 05/03/16.
 */
@RestController
@RequestMapping("/api/students")
public class StudentService {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents() {
        return Arrays.asList(Student.builder().age(10).name("Anand").build());
    }

}
