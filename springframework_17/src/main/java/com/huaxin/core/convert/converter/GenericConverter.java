package com.huaxin.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

public interface GenericConverter {
    Object convert(Object source, Class<?> sourceType, Class<?> targetType);
    
    final class ConvertiblePair{
        private final Class<?> sourceType;
        private final Class<?> targetType;
        
        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType,"sourceType is null!!!");
            Assert.notNull(targetType,"targetType is null!!!");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(null == obj || obj.getClass() != ConvertiblePair.class){
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);
        }
    }
    
    Set<ConvertiblePair> getConvertibleTypes();
}
