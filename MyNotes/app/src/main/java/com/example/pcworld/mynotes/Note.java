package com.example.pcworld.mynotes;

/**
 * Created by pc world on 08/02/2017.
 */

public class Note {
    private String title;
    private String description;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!title.equals(""))
            this.title = title;
        else
            this.title = "N/A";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
