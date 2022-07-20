package ru.itfb.authorservice.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.itfb.authorservice.interfaces.AuthorRepository;
import ru.itfb.authorservice.model.Author;

import java.util.HashSet;
import java.util.Set;

@Component
public class demodata {
    private final AuthorRepository repository;

    public demodata(AuthorRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void addData(ApplicationReadyEvent event) {
        String fName="Василий";
        String lName="Петрович";

        if(!repository.existsAuthorByFirstNameAndLastName(fName, lName)) {
            Set<String> isbns = new HashSet<>();
            isbns.add("00-0000");
            Author author = new Author();
            author.setFirstName("Василий");
            author.setLastName("Петрович");
            author.setIsbn(isbns);
            repository.save(author);
        }


    }
}
