package com.huaxin.beans.bean;

import com.huaxin.beans.aware.ApplicationContextAware;
import com.huaxin.beans.aware.BeanClassLoaderAware;
import com.huaxin.beans.aware.BeanFactoryAware;
import com.huaxin.beans.aware.BeanNameAware;
import com.huaxin.beans.factory.BeanFactory;
import com.huaxin.beans.lifecycle.DisposableBean;
import com.huaxin.beans.lifecycle.InitializingBean;
import com.huaxin.context.ApplicationContext;

public class UserService implements InitializingBean, DisposableBean, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware {
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private String id;
    private String company;
    private String location;
    private UserDao userDao;

    public UserService() {
    }

    public void query(){
        System.out.println("name:" + userDao.queryInfo(id) + ",company:" + company + ",location:" + location);
    }

    public void setLocation(String location){
        this.location = location;
    }

    @Override
    public void destroy() {
        System.out.println("执行: UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行: UserService.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println(classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println(beanName);
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
