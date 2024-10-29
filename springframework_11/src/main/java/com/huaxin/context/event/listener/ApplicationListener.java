package com.huaxin.context.event.listener;

import com.huaxin.context.event.ApplicationEvent;

import java.util.EventListener;

public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    // 处理应用上下文的事件
    void onApplicationEvent(E event);

}
