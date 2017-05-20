package com.example.win81.bab_showtext;

/**
 * Created by WIN 8.1 on 8/9/2016.
 */
public class EventModel {
    private String event;
    private int type;

    public  EventModel (int type,String event){
        this.type=type;
        this.event=event;
    }

    public String getEvent() {
        return event;
    }

    public int getType() {
        return type;
    }



}
