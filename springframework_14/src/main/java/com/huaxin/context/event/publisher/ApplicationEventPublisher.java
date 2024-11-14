package com.huaxin.context.event.publisher;

import com.huaxin.context.event.ApplicationEvent;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
