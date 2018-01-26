package com.task.content;

import java.util.List;

public class Content {
    private String title;
    private List<Segment> page;

    public Content() {
    }

    public Content(List<Segment> page) {
        this.page = page;
    }

    public Content(String title, List<Segment> page) {
        this.title = title;
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Segment> getPage() {
        return page;
    }

    public void setPage(List<Segment> page) {
        this.page = page;
    }
}
