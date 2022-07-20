package ru.itfb.bookservice.model.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDto implements Serializable {
    private final long id;
    private final String name;
    private final String review;
    private final String style;
}
