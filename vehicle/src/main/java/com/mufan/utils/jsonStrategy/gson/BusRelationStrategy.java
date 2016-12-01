package com.mufan.utils.jsonStrategy.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/29.
 */
public class BusRelationStrategy  implements ExclusionStrategy {

    public List<String> fields = new ArrayList<String>();

    public BusRelationStrategy() {
        fields.add("buses");
        fields.add("bus");
        fields.add("reboot");
        fields.add("factoryReset");
        fields.add("profileName");
        fields.add("lastContact");
    }
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fields.contains(fieldAttributes.getName());
    }

    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
