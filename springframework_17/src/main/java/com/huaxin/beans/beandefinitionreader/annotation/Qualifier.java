package com.huaxin.beans.beandefinitionreader.annotation;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER,ElementType.METHOD})
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
