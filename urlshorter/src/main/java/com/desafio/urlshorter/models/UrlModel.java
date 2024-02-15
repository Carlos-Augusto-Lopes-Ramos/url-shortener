package com.desafio.urlshorter.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="url")
public class UrlModel extends RepresentationModel<UrlModel> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String link;

    private String urlshort;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    private String day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrlshort() {
        return urlshort;
    }

    public void setUrlshort(String urlshort) {
        this.urlshort = urlshort;
    }
}
