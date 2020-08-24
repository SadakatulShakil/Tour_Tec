package com.example.tourtec.Model;

public class Events {
    private String eventId;
    private String createdBy;
    private String eventName;
    private String eventDescription;
    private String eventBudget;
    private String eventStartTime;
    private String eventEndTime;
    private String addingTime;

    public Events() {
    }

    public Events(String eventId, String createdBy, String eventName,
                  String eventDescription, String eventBudget, String eventStartTime, String eventEndTime, String addingTime) {
        this.eventId = eventId;
        this.createdBy = createdBy;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventBudget = eventBudget;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.addingTime = addingTime;
    }

    public String getAddingTime() {
        return addingTime;
    }

    public void setAddingTime(String addingTime) {
        this.addingTime = addingTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Events{" +
                "eventId='" + eventId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventBudget='" + eventBudget + '\'' +
                ", eventStartTime='" + eventStartTime + '\'' +
                ", eventEndTime='" + eventEndTime + '\'' +
                ", addingTime='" + addingTime + '\'' +
                '}';
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(String eventBudget) {
        this.eventBudget = eventBudget;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

}
