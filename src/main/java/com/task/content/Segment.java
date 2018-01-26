package com.task.content;

import java.util.List;

public class Segment {
    private List<Paragraph> writings;   //背景图片
    private List<String> images;          //手机中每个页面中的图片

    public Segment() {
    }

    public Segment(List<Paragraph> writings, List<String> images) {
        this.writings = writings;
        this.images = images;
    }

    public List<Paragraph> getWritings() {
        return writings;
    }

    public void setWritings(List<Paragraph> writings) {
        this.writings = writings;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
