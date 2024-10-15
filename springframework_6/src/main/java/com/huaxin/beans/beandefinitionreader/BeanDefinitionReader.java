package com.huaxin.beans.beandefinitionreader;

import com.huaxin.beans.beandefinition.BeanDefinitionRegistry;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resourceloader.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource);
    void loadBeanDefinitions(Resource... resources);
    void loadBeanDefinitions(String location);
    void loadBeanDefinitions(String... locations);
}
