package com.huaxin.aop.autoproxy;

import com.huaxin.aop.advisedsupport.AdvisedSupport;
import com.huaxin.aop.advisedsupport.TargetSource;
import com.huaxin.aop.advisor.Advisor;
import com.huaxin.aop.advisor.AspectJExpressionPointcutAdvisor;
import com.huaxin.aop.pointcutexpression.ClassFilter;
import com.huaxin.aop.pointcutexpression.Pointcut;
import com.huaxin.aop.proxyfactory.ProxyFactory;
import com.huaxin.beans.aware.BeanFactoryAware;
import com.huaxin.beans.beandefinition.PropertyValues;
import com.huaxin.beans.exception.BeanException;
import com.huaxin.beans.factory.BeanFactory;
import com.huaxin.beans.factory.DefaultListableBeanFactory;
import com.huaxin.beans.postprocessor.bean.InstantiationAwareBeanPostProcessor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Map;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) {
        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean) {
        Class<?> beanClass = bean.getClass();
        if(isInfrastructureClass(beanClass)){
            return bean;
        }
        Map<String, AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors.values()) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(bean);;
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}

