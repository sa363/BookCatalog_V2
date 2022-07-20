package ru.itfb.bookservice.model;

import lombok.Data;
import ru.itfb.bookservice.model.pojo.AuthorDTO;

import java.io.Serializable;
import java.util.List;

@Data
public class BookDto implements Serializable {
    private final long id;
    private final String name;
    private final String review;
    private final String style;
    private final String isbn;
}
