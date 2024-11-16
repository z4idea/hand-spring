package com.huaxin.aop.advisor;

import com.huaxin.aop.interceptor.MethodBeforeAdviceInterceptor;
import com.huaxin.aop.pointcutexpression.AspectJExpressionPointcut;
import com.huaxin.aop.pointcutexpression.Pointcut;
import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{
    private AspectJExpressionPointcut pointcut;
    private Advice advice;
    private String expression;

    public void setAdvice(Advice advice) {
        //this.advice = advice;
        this.advice = new MethodBeforeAdviceInterceptor();
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if(pointcut == null){
            return new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
