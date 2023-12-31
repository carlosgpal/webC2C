package com.backend.dto;

// This is the DTO for the Tag model of the MySQL database
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
