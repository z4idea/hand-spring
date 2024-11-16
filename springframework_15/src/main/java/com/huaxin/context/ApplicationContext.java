package com.huaxin.context;

import com.huaxin.beans.factory.HierarchicalBeanFactory;
import com.huaxin.beans.factory.ListableBeanFactory;
import com.huaxin.context.event.publisher.ApplicationEventPublisher;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ApplicationEventPublisher {
}
