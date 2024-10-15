package com.huaxin.core.io.resource;


import cn.hutool.core.lang.Assert;
import com.huaxin.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"path不允许为空");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }


    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null){
            throw new FileNotFoundException("无法找到 " + path + " 这个路径下的文件");
        } else {
            return is;
        }
    }
}
