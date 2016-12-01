package com.mufan.utils.jsonStrategy.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/29.
 */
public class HostsRelationStrategy implements ExclusionStrategy {
    public List<String> fields = new ArrayList<String>();

    public HostsRelationStrategy() {
        fields.add("hosts");
        fields.add("reboot");
        fields.add("factoryReset");
        fields.add("profileName");
        fields.add("buses");
        fields.add("mapPoints");
    }
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fields.contains(fieldAttributes.getName());
    }

    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
