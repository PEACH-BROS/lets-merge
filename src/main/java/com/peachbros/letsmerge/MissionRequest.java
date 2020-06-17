package com.peachbros.letsmerge;

public class MissionRequest {

    private String name;
    private String startDateTime;
    private String dueDateTime;

    private MissionRequest() {

    }

    public MissionRequest(String name, String startDateTime, String dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public String getName() {
        return name;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return "MissionRequest{" +
                "name='" + name + '\'' +
                ", startDateTime='" + startDateTime + '\'' +
                ", dueDateTime='" + dueDateTime + '\'' +
                '}';
    }
}
