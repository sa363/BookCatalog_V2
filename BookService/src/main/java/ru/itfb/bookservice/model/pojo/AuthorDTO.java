package ru.itfb.bookservice.model.pojo;

import lombok.*;
import ru.itfb.bookservice.model.Base;

import java.util.List;
import java.util.Objects;

@Data
public class AuthorDTO extends Base {
        private String firstName;
        private String lastName;

        @Override
        public String toString() {
            return "Author{" +
                    "fullName='" + firstName + " " + lastName + '\'' + '}';
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(firstName, authorDTO.firstName) && Objects.equals(lastName, authorDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
