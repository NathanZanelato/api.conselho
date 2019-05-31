package org.conselhotutelar.modelos;

import java.util.LinkedHashMap;

public class DynamicDto extends LinkedHashMap<String, Object> {

    public static DynamicDto build() {
        return new DynamicDto();
    }

    public DynamicDto with(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public DynamicDto withInteger(String key, Integer value) {
        this.put(key, value);
        return this;
    }

}
