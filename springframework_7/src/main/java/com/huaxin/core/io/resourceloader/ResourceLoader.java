package com.huaxin.core.io.resourceloader;

import com.huaxin.core.io.resource.Resource;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";
    Resource getResource(String location);
}
