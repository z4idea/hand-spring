package com.huaxin.aop.advisor;

import com.huaxin.aop.pointcutexpression.Pointcut;

public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
