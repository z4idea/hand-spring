package com.huaxin.core.io.resourceloader;

import com.huaxin.core.io.resource.ClassPathResource;
import com.huaxin.core.io.resource.FileSystemResource;
import com.huaxin.core.io.resource.Resource;
import com.huaxin.core.io.resource.UrlResource;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        if(location.contains(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
