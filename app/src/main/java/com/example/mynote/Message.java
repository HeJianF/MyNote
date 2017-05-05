package com.example.mynote;

import org.litepal.crud.DataSupport;

/**
 * Created by 日不落 on 2017/5/4.
 */

public class Message extends DataSupport{

    private String time;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
