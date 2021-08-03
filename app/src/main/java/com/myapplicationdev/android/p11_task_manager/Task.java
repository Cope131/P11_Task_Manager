package com.myapplicationdev.android.p11_task_manager;

public class Task {
    private int id;
    private String name, description;
    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%d %s \n%s", id, name, description);
    }
}
