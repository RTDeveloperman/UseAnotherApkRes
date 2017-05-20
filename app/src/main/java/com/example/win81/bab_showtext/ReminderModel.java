package com.example.win81.bab_showtext;

/**
 * Created by WIN 8.1 on 8/10/2016.
 */
public class ReminderModel {
    private String groupName;
    private int index;

    public ReminderModel(String groupName, int index) {
        this.groupName = groupName;
        this.index = index;
    }
    public ReminderModel() {

    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
