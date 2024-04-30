package steps;

import lombok.Data;

@Data
public class Endpoints {
    public static final String SAVE_NEW_AUTHORS_URL = "/library/authors/save";
    public static final String SAVE_NEW_BOOK_URL = "/library/books/save";
    public static final String GET_ALL_BOOKS_URL = "/library/authors/{id}/books";
    public static final String GET_ALL_BOOKS_XML_URL = "/library/authors/books";
}
