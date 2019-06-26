package org.conselhotutelar.modelos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class DynamicDto extends LinkedHashMap<String, Object> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

    public Date getDate(String key) {
        try {
            String dtString = getString(key);
            return dtString != null ? sdf.parse(dtString) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getLong(String key){
        return this.get(key) != null ? Long.parseLong(this.get(key).toString()) : null;
    }

    public String getString(String key){
        return this.get(key) != null ? String.valueOf(this.get(key)) : null;
    }

    public Integer getInt(String key){
        return this.get(key) != null ? Integer.parseInt(getString(key)) : null;
    }

}
