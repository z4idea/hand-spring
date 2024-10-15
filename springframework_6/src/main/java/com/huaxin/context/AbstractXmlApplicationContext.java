package com.huaxin.context;

import com.huaxin.beans.beandefinitionreader.XmlBeanDefinitionReader;
import com.huaxin.beans.factory.DefaultListableBeanFactory;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory factory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory,this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
