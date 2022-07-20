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

package ru.itfb.authorservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itfb.authorservice.exception.AuthorNotFoundException;
import ru.itfb.authorservice.interfaces.AuthorRepository;
import ru.itfb.authorservice.model.Author;

import ru.itfb.authorservice.model.pojo.AuthorView;
import ru.itfb.authorservice.service.AuthorCatalogService;

import java.util.List;

@Service
@Slf4j
public class AuthorCatalogServiceImpl implements AuthorCatalogService {

    private final AuthorRepository authorRepository;


    public AuthorCatalogServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }




    @Override
    @Transactional
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public boolean deleteAuthor(Long id) {
        authorRepository.delete(
                authorRepository.findById(id).orElseThrow(() ->
                        new AuthorNotFoundException(String.format("No author with id %s is available", id))));
        return true;
    }

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(String.format("No author with id %s is available", id)));
    }


    /**
     * @param isbn
     * @return
     */
    @Override
    public List<AuthorView> getAuthorsByIsbn(String isbn) {
        return authorRepository.getAuthorByIsbn(isbn);
    }
}
