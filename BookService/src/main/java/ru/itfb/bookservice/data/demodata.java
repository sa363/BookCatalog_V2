package ru.itfb.bookservice.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.itfb.bookservice.interfaces.BookRepository;
import ru.itfb.bookservice.model.Book;

@Component
public class demodata {
    private final BookRepository bookRepository;

    public demodata(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener
    public void addData(ApplicationReadyEvent event) {
        Book book1 = new Book();
        book1.setName("Первая книга");
        book1.setReview("Тра ля ля");
        book1.setStyle("Ужастик");
        book1.setIsbn("00-0000");
//        bookRepository.save(book1);
    }
}
