package com.bobocode.basics;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Exercise {
    public String name() default "";
    public Level complexityLevel() default Level.BASIC;
    public String value() default "";
}
