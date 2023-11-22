package com.c2c.dto;

public class TagDTO {

    private String idtag;
    private String name;

    public TagDTO() {
    }

    public TagDTO(String idtag, String name) {
        this.idtag = idtag;
        this.name = name;
    }

    public String getIdtag() {
        return idtag;
    }

    public void setIdtag(String idtag) {
        this.idtag = idtag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
