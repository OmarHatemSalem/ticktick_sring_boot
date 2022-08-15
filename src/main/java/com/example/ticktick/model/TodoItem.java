package com.example.ticktick.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoItem {
    private final UUID id;
    
    @NotBlank    
    private final String name;  

    private final Date date;

    private final String descrption;

    private boolean isComplete = false;

    /*public TodoItem(@JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("date") Date date,
            @JsonProperty("desc") String desc) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.descrption = desc;
    }*/
    
    public TodoItem(@JsonProperty("id") UUID id,
                    @JsonProperty("name") String name,
                    @JsonProperty("date") Date date,
                    @JsonProperty("desc") String desc,
                    @JsonProperty("comp") boolean comp) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.descrption = desc;
        this.isComplete = comp;
    }

    public UUID getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDesc() {
        return this.descrption;
    }

    public boolean getComp() {
        return this.isComplete;
    }

    public void toggleComp() {
        if (this.isComplete) {this.isComplete = false;}
        else {this.isComplete = true;}
    } 
}
