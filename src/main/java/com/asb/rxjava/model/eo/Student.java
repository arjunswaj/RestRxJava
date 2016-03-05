package com.asb.rxjava.model.eo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by arjun on 05/03/16.
 */
@Getter
@Setter
@Builder
public class Student {
    private String name;
    private int age;
}
