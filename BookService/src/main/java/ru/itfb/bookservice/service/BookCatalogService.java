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

package ru.itfb.bookservice.service;

import ru.itfb.bookservice.model.Book;
import ru.itfb.bookservice.model.BookView;
import ru.itfb.bookservice.model.pojo.AuthorDTO;
import ru.itfb.bookservice.model.pojo.BookDto;

import java.util.List;

public interface BookCatalogService {

    Book addBook(Book book);

    BookView getBookById(long id);
    <T> T getBookById(long id, Class<T> type);

    Book updateBookById(Book newBook, Long id);
    boolean removeBookById(Long id);

    List<AuthorDTO> getAuthors(String isbn);
}
