package com.codegym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Commenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private Date time;

    public Commenter() {
    }

    public Commenter(Long id, String title, String content, Date time){
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
