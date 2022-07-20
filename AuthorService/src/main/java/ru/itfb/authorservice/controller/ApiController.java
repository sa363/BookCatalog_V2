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

package ru.itfb.authorservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itfb.authorservice.model.Author;
import ru.itfb.authorservice.model.pojo.AuthorView;
import ru.itfb.authorservice.service.impl.AuthorCatalogServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    private AuthorCatalogServiceImpl service;

    public ApiController(AuthorCatalogServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/author")
    Iterable<Author> allAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/author/{id}")
    Author GetAuthorById(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

    @PostMapping("/author")
    Author NewAuthor(@RequestBody Author author) {
        return service.addAuthor(author);
    }

    @DeleteMapping("/author/{id}")
    ResponseEntity<?> DeleteAuthor(@PathVariable Long id) {
        return service.deleteAuthor(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @GetMapping("/author/isbn/{isbn}")
    List<AuthorView> GetAuthorByIsbn(@PathVariable String isbn) {
        return service.getAuthorsByIsbn(isbn);
    }
}
