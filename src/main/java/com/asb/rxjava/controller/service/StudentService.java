package com.asb.rxjava.controller.service;

import com.asb.rxjava.model.eo.Student;
import com.asb.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Student Service.
 * Created by arjun on 05/03/16.
 */
@Named
public class StudentService {
    private Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Inject
    private ExecutorService executorService;

    private Random random = new Random();
    private List<String> NAMES = Arrays.asList("Arjun", "Aditya", "Saurab", "Gunjan", "Ashutosh", "Pooja", "Ahsan", "Madhu", "Azad", "Manju");

    /**
     * Get the observable of Students
     *
     * @return observable of students
     */
    public Observable<Student> getStudents() {
        return Observable.<Student>create(
                subscriber -> {
                    NAMES.parallelStream()
                            .forEach(name -> createStudent(subscriber, name));
                    subscriber.onCompleted();
                    LOGGER.info("Completed the request.");
                }).subscribeOn(Schedulers.from(executorService));
    }

    /**
     * Get the Student by name
     *
     * @param name name of the Student
     * @return Observable of students
     */
    public Observable<Student> getStudentByName(String name) {
        return Observable.<Student>create(
                subscriber -> {
                    NAMES.parallelStream()
                            .filter(s -> s.equals(name))
                            .findFirst()
                            .ifPresent(studentName -> createStudent(subscriber, studentName));
                    subscriber.onCompleted();
                    LOGGER.info("Completed the request.");
                }
        ).subscribeOn(Schedulers.from(executorService));
    }

    private void createStudent(Subscriber<? super Student> subscriber, String studentName) {
        final int age = random.nextInt(20);
        LOGGER.info(studentName + " will be built in " + age / 10 + " seconds.");
        Sleeper.sleep(age * 100);
        subscriber.onNext(Student.builder().name(studentName).age(age).build());
        LOGGER.info(studentName + " is built.");
    }

}
