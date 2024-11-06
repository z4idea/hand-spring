package com.huaxin.beans.beandefinitionreader.scan;

import cn.hutool.core.util.ClassUtil;
import com.huaxin.beans.beandefinition.BeanDefinition;
import com.huaxin.beans.beandefinitionreader.annotation.Component;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> aClass : classes) {
            candidates.add(new BeanDefinition(aClass));
        }
        return candidates;
    }
}
