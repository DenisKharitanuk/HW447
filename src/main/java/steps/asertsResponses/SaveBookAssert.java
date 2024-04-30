package steps.asertsResponses;

import models.positive_responses.SaveNewBooksPositiveResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveBookAssert {


    public static void verifyBodySaveBook(SaveNewBooksPositiveResponse book, long bookId){
        assertEquals(book.getBookId(),bookId);
    }
}
