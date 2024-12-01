package com.huaxin.aop.pointcutexpression;

public interface Pointcut {
    // 获取类过滤器
    ClassFilter getClassFilter();
    // 获取方法匹配器
    MethodMatcher getMethodMatcher();
}
