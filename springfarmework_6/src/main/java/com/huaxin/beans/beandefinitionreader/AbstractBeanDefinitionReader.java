package com.huaxin.beans.beandefinitionreader;

import com.huaxin.beans.beandefinition.BeanDefinitionRegistry;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resourceloader.DefaultResourceLoader;
import com.huaxin.core.io.resourceloader.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(beanDefinitionRegistry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }


    public abstract void loadBeanDefinitions(Resource resource);
    public abstract void loadBeanDefinitions(Resource... resources);
    public abstract void loadBeanDefinitions(String location);
    public abstract void loadBeanDefinitions(String... locations);
}
