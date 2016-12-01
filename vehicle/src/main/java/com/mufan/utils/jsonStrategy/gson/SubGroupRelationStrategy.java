package com.mufan.utils.jsonStrategy.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswe on 2016/7/25.
 */
public class SubGroupRelationStrategy implements ExclusionStrategy {

    public List<String> fields = new ArrayList<String>();

    public SubGroupRelationStrategy() {
        fields.add("subGroup");
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

    /*private SubGroupRelationStrategy() {
    }

    private static SubGroupRelationStrategy subGroupStrategy;

    public static SubGroupRelationStrategy getSubGroupStrategy(){
        if (subGroupStrategy == null) {
            synchronized (SubGroupRelationStrategy.class) {
                if (subGroupStrategy == null) {
                    subGroupStrategy = new SubGroupRelationStrategy();
                }
            }
        }
        return subGroupStrategy;
    }*/
}
