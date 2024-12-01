package com.huaxin.core.convert.service;

import com.sun.istack.internal.Nullable;

public interface ConversionService {
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);
    <T> T convert(Object source, Class<T> targetType);
}
