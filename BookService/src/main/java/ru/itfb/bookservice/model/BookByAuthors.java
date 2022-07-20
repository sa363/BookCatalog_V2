package ru.itfb.bookservice.model;

import lombok.*;
import ru.itfb.bookservice.model.pojo.AuthorDTO;

import java.io.Serializable;
import java.util.List;

@Data

public class BookByAuthors implements Serializable {
    private  long id;
    private  String name;
    private  String review;
    private  String style;
    private  String isbn;
    private List<AuthorDTO> authors;


//    public void setAuthors(List<AuthorDTO> authors) {
//        this.authors = authors;
//    }



}
