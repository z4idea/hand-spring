package com.huaxin.factory;

import com.huaxin.beandefinition.BeanDefinition;
import com.huaxin.instantiationstrategy.CglibSubclassingInstantiationStrategy;
import com.huaxin.instantiationstrategy.InstantiationStrategy;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    //private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    @Override
    protected Object createBean(Object[] args, String beanName, BeanDefinition beanDefinition) {
        Object bean = null;

        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            if(null != args &&
                    // 检验入参个数
                    args.length
                    == declaredConstructor.getParameterTypes().length
            ){
                // 检验入参类型
                boolean flag = true;
                for (int i = 0; i < args.length; i++) {
                    // 需要注意的是
                    // args[i].getClass()会取到对应类型的包装类型(自动装箱原理)
                    if(args[i].getClass() != declaredConstructor.getParameterTypes()[i]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    bean = getInstantiationStrategy().instantiate(beanDefinition,beanName,declaredConstructor,args);
                    break;
                }
            }
        }
        // 不要忘记添加注册单例bean
        registerSingletonBean(beanName,bean);
        return bean;
    }
}
