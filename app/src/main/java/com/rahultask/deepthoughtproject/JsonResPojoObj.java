package com.rahultask.deepthoughtproject;

import org.json.JSONArray;

public class JsonResPojoObj {

    String dataId, shortDescription, taskDescription, taskTitle, picture, category, fullName;
    JSONArray preRequisites, learningOutcomes;

    public JsonResPojoObj(String dataId, String shortDescription, String taskDescription,
                          String taskTitle, String picture, String category, String fullName,
                          JSONArray preRequisites, JSONArray learningOutcomes) {
        this.dataId = dataId;
        this.shortDescription = shortDescription;
        this.taskDescription = taskDescription;
        this.taskTitle = taskTitle;
        this.picture = picture;
        this.category = category;
        this.fullName = fullName;
        this.preRequisites = preRequisites;
        this.learningOutcomes = learningOutcomes;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public JSONArray getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(JSONArray preRequisites) {
        this.preRequisites = preRequisites;
    }

    public JSONArray getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(JSONArray learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
}
