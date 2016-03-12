package com.asb.rxjava.controller.rest;

import com.asb.rxjava.controller.service.StudentService;
import com.asb.rxjava.model.eo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.inject.Inject;
import java.util.List;

/**
 * Student Controller.
 * Created by arjun on 05/03/16.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    /**
     * Student Service.
     */
    @Inject
    private StudentService studentService;

    /**
     * Logger.
     */
    private Logger LOGGER = LoggerFactory.getLogger(StudentController.class);


    /**
     * Gets all the students.
     *
     * @return List of students
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<List<Student>> getStudents() {
        LOGGER.info("Requesting in " + Thread.currentThread().getName() + " man");
        DeferredResult<List<Student>> deferredResult = new DeferredResult<>(200000L);
        studentService.getStudents().toList().subscribe(students -> {
            deferredResult.setResult(students);
            LOGGER.info("Collected results from " + Thread.currentThread().getName() + " man");
        });
        LOGGER.info("Exiting from " + Thread.currentThread().getName() + " man");
        return deferredResult;
    }
}
