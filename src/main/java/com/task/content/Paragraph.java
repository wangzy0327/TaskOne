package com.task.content;

import java.util.List;

public class Paragraph {
    private List<String> scripts;

    public Paragraph(List<String> scripts) {
        this.scripts = scripts;
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }
}
