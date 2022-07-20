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

package ru.itfb.authorservice.service;

import ru.itfb.authorservice.model.Author;
import ru.itfb.authorservice.model.pojo.AuthorView;

import java.util.List;


public interface AuthorCatalogService {

    Author addAuthor(Author author);
    boolean deleteAuthor(Long id);

    Iterable<Author> getAllAuthors();

    Author getAuthorById(Long id);

    List<AuthorView> getAuthorsByIsbn(String isbn);


}
