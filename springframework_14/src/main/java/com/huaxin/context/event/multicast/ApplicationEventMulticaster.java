package com.huaxin.context.event.multicast;

import com.huaxin.context.event.ApplicationEvent;
import com.huaxin.context.event.listener.ApplicationListener;

public interface ApplicationEventMulticaster {
    // 添加监听
    void addApplicationListener(ApplicationListener<?> listener);

    // 删除监听
    void removeApplicationListener(ApplicationListener<?> listener);

    // 推送事件信息
    void multicastEvent(ApplicationEvent event);
}
