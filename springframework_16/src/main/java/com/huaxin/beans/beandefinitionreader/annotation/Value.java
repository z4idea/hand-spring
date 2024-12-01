package com.huaxin.beans.beandefinitionreader.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Documented
public @interface Value {
    String value();
}
