package com.huaxin.beans.beandefinitionreader.scan;

import cn.hutool.core.util.StrUtil;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinition.BeanDefinitionRegistry;
import com.huaxin.beans.beandefinitionreader.annotation.Component;
import com.huaxin.beans.beandefinitionreader.annotation.Scope;
import com.huaxin.beans.postprocessor.bean.AutowiredAnnotationBeanPostProcessor;

import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }
    
    public void doScan(String... basePackages){
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidateComponents) {
                String beanScope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotBlank(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition),beanDefinition);
            }
        }
        // 注册处理注解的 BeanPostProcessor
        registry.registerBeanDefinition(StrUtil.lowerFirst(AutowiredAnnotationBeanPostProcessor.class.getSimpleName()),new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }
    
    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Scope scope = (Scope) beanClass.getAnnotation(Scope.class);
        if(null != scope){
            return scope.value();
        }
        return StrUtil.EMPTY;
    }
    
    private String determineBeanName(BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Component component = (Component) beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isBlank(value)){
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
