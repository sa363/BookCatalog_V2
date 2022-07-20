/*
 *
 *  * Copyright 2002-2022 the Sergey Anisimov <s.anisimov@gmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

package ru.itfb.bookservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.itfb.bookservice.exception.BookNotFoundException;
import ru.itfb.bookservice.interfaces.BookRepository;
import ru.itfb.bookservice.model.Book;
import ru.itfb.bookservice.model.BookByAuthors;
import ru.itfb.bookservice.model.BookDto;
import ru.itfb.bookservice.model.BookView;
import ru.itfb.bookservice.model.pojo.AuthorDTO;
import ru.itfb.bookservice.service.BookCatalogService;

import java.util.List;

@Service
@Slf4j
public class BookCatalogServiceImpl implements BookCatalogService {

    private final BookRepository bookRepository;

    private final RestTemplate restTemplate;

    public BookCatalogServiceImpl(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

//    @Override
//    public Book getBookById(long id) {
//        return bookRepository.findById(id).orElseThrow(() ->
//                new BookNotFoundException(String.format("No book with id %s is available", id)));
//    }

    @Override
    public BookView getBookById(long id) {
        return bookRepository.getBookById(id);
////        return bookRepository.findById(id).orElseThrow(() ->
////                new BookNotFoundException(String.format("No book with id %s is available", id)));
    }
    @Override
    public <T> T getBookById(long id, Class<T> type) {
        return bookRepository.getBookById(id, type);
////        return bookRepository.findById(id).orElseThrow(() ->
////                new BookNotFoundException(String.format("No book with id %s is available", id)));
    }



    @Override
    @Transactional
    public Book updateBookById(Book newBook, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(String.format("No book with id %s is available", id)));
        if (newBook.getName() != null) {
            book.setName(newBook.getName());
        }
        if (newBook.getReview() != null) {
            book.setReview(newBook.getReview());
        }
        if (newBook.getStyle() != null) {
            book.setStyle(newBook.getStyle());
        }
        bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional
    public boolean removeBookById(Long id) {

        bookRepository.delete(
        bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(String.format("No book with id %s is available", id))));
        return true;
    }

    /**
     * @param isbn
     * @return
     */

    private List<AuthorDTO> getAuthors(String isbn) {
        AuthorDTO[] authors  = restTemplate.getForObject("http://localhost:8081/api/author/isbn/"+isbn, AuthorDTO[].class);
        return List.of(authors);
    }

    public BookByAuthors getBookById2(long id) {
        Book book = bookRepository.getBookById(id, Book.class);
        BookByAuthors bookByAuthors = convertToDTO(book);
        bookByAuthors.setAuthors(getAuthors(book.getIsbn()));
        return bookByAuthors;
    }

    private BookByAuthors convertToDTO(Book book)  {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(book, BookByAuthors.class);
    }
}
