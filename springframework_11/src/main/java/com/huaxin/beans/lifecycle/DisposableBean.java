package com.huaxin.beans.lifecycle;

public interface DisposableBean {
    // 销毁方法
    void destroy() throws Exception;
}
