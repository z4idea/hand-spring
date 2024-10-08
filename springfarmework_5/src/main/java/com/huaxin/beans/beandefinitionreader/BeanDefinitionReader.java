package com.huaxin.beans.beandefinitionreader;

import com.huaxin.beans.beandefinition.BeanDefinitionRegistry;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resourceloader.ResourceLoader;

import java.io.IOException;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws IOException;

    void loadBeanDefinitions(Resource... resources) throws IOException;

    void loadBeanDefinitions(String location) throws IOException;
}
