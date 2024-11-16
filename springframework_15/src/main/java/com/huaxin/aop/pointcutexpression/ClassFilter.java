package com.huaxin.aop.pointcutexpression;

public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
