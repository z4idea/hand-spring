package com.huaxin.beans.lifecycle;

public interface InitializingBean {
    // bean处理了属性填充后调用
    void afterPropertiesSet();
}
