package com.backend.dto;

// This is the DTO for the Image model of the MySQL database
public class ImageDTO {

    private String idimage;
    private String link;

    public ImageDTO() {
    }

    public ImageDTO(String idimage, String link) {
        this.idimage = idimage;
        this.link = link;
    }

    public String getIdimage() {
        return idimage;
    }

    public void setIdimage(String idimage) {
        this.idimage = idimage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
