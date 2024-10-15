package com.huaxin.beans.beandefinitionreader;

import com.huaxin.beans.beandefinition.BeanDefinitionRegistry;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resourceloader.DefaultResourceLoader;
import com.huaxin.core.io.resourceloader.ResourceLoader;

import java.io.IOException;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public abstract void loadBeanDefinitions(Resource resource) throws IOException;

    public abstract void loadBeanDefinitions(Resource... resources) throws IOException;

    public abstract void loadBeanDefinitions(String location) throws IOException;
}
