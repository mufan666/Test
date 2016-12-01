package com.mufan.ap.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Created by aswe on 2016/7/20.
 */
@Entity
@Table(name = "scriptbean")
public class Script {
    private String name;
    private String script;
    private String description;

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Lob
    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
