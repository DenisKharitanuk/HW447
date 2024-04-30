package steps.asertsResponses;

import entity.Author;
import models.positive_responses.GetAllAuthorsBooksPositiveResponseXML;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllBookAssertXML {
    public static void verifyBodyGetBooksXML(GetAllAuthorsBooksPositiveResponseXML books, String bookTitle,Author author) {


        assertEquals(books.getBook().get(0).getBookTitle(), bookTitle);
        assertEquals(books.getBook().get(0).getAuthor(), author);
    }
}
