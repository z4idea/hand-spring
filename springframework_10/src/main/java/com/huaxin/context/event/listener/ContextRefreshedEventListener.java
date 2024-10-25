package com.huaxin.context.event.listener;

import com.huaxin.context.event.ContextClosedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextClosedEvent>{

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
