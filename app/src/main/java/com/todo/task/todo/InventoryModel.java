package com.todo.task.todo;

public class InventoryModel {

    private String img_url;

    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public String getStatus_url() {
        return status_url;
    }

    public String getStatus() {
        return status;
    }

    public String getIssue_name() {
        return issue_name;
    }

    public String getPer_name() {
        return per_name;
    }

    private String name;
    private String status_url;
    private String status;
    private String issue_name;
    private String per_name;

    public InventoryModel(String img_url, String name, String status_url, String status, String issue_name, String per_name)
    {
        this.img_url = img_url;
        this.name = name;
        this.status_url = status_url;
        this.status = status;
        this.issue_name = issue_name;
        this.per_name = per_name;
    }


}
