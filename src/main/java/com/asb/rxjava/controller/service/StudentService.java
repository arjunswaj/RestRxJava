package com.asb.rxjava.controller.service;

import com.asb.rxjava.model.eo.Student;
import com.asb.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

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
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private Random random = new Random();
    private List<String> NAMES = Arrays.asList("Arjun", "Aditya", "Saurab", "Gunjan", "Ashutosh", "Pooja", "Ahsan", "Madhu", "Azad", "Manju");

    public Observable<Student> getStudents() {
        return Observable.<Student>create(
                subscriber -> {
                    NAMES.parallelStream().forEach(name ->
                            {
                                final int age = random.nextInt(20);
                                LOGGER.info(name + " will be built in " + age + " seconds.");
                                Sleeper.sleep(age * 1000);
                                subscriber.onNext(Student.builder().name(name).age(age).build());
                                LOGGER.info(name + " is built.");
                            }
                    );
                    subscriber.onCompleted();
                    LOGGER.info("Completed the request.");
                }).subscribeOn(Schedulers.from(executorService));
    }
}
