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

package ru.itfb.bookservice.controller;

import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itfb.bookservice.model.Book;
import ru.itfb.bookservice.model.pojo.AuthorDTO;
import ru.itfb.bookservice.model.pojo.BookDto;
import ru.itfb.bookservice.service.impl.BookCatalogServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    private final BookCatalogServiceImpl service;

    public ApiController(BookCatalogServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/book")
    @Counted(value = "addbook")
    Book NewBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/book/{id}")
    @Counted(value = "getbook")
    BookDto GetBookById(@PathVariable long id) {
        log.info("Get book by id {}", id);
        return service.getBookById(id, BookDto.class);
    }

    @PutMapping("/book/{id}")
    @Counted(value = "updatebook")
    Book updateBookById(@PathVariable long id, @RequestBody Book book) {
        return service.updateBookById(book, id);
    }

    @DeleteMapping("/book/{id}")
    @Counted(value = "deletebook")
    ResponseEntity<?> removeBookById(@PathVariable long id) {
        return service.removeBookById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/book/isbn/{isbn}")
    List<AuthorDTO> getAuthors(@PathVariable String isbn) {
        return service.getAuthors(isbn);
    }








}
