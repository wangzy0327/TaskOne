package com.task.enums;

public enum UrlEnum {

    SUCCESS(1,"操作成功"),
    FAIL(0,"操作失败"),
    REPEAT_INSERT(-1,"重复操作");

    private int state;
    private String info;

    UrlEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static UrlEnum stateOf(int index)
    {
        for (UrlEnum state : values())
        {
            if (state.getState()==index)
            {
                return state;
            }
        }
        return null;
    }
}
