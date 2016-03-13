package com.asb.rxjava.controller.rest;

import com.asb.rxjava.controller.service.StudentService;
import com.asb.rxjava.model.eo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.inject.Inject;
import java.util.List;

/**
 * Student Controller.
 * Created by arjun on 05/03/16.
 */
@CrossOrigin(origins = {"http://localhost:8000"})
@RestController
@RequestMapping("/api/student")
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
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<Student> getStudentByName(@PathVariable("name") String name) {
        LOGGER.info("Requesting in " + Thread.currentThread().getName() + " man");
        DeferredResult<Student> deferredResult = new DeferredResult<>(200000L);
        studentService.getStudentByName(name).subscribe(student -> {
            deferredResult.setResult(student);
            LOGGER.info("Collected result from " + Thread.currentThread().getName() + " man");
        }, throwable -> {
            deferredResult.setErrorResult(throwable);
            LOGGER.error("Exception from " + Thread.currentThread().getName() + " man", throwable);
        }, () -> {
            if (!deferredResult.hasResult()) {
                deferredResult.setResult(null);
                LOGGER.warn("No result from " + Thread.currentThread().getName() + " man");
            }
        });
        LOGGER.info("Exiting from " + Thread.currentThread().getName() + " man");
        return deferredResult;
    }

    /**
     * Gets all the students.
     *
     * @return List of students
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
