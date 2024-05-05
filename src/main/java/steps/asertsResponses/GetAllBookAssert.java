package steps.asertsResponses;

import entity.Book;
import io.restassured.response.ValidatableResponse;
import models.positive_responses.GetAllAuthorsBooksPositiveResponse;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetAllBookAssert {
    public static void verifyBodyGetBook(List<GetAllAuthorsBooksPositiveResponse> allBooks, long id, String bookTitle) {
        Optional<GetAllAuthorsBooksPositiveResponse> firstResponseOptional = allBooks.stream().findFirst();
        GetAllAuthorsBooksPositiveResponse firstResponse = firstResponseOptional.get();
        assertEquals(firstResponse.getBookTitle(), bookTitle);
        assertEquals(firstResponse.getAuthor().getId(), id);
    }

    public static void verifyBodyGetBooks(List<GetAllAuthorsBooksPositiveResponse> allBooks, long id, List<String> bookTitles) {
        int i = 0;
        for (GetAllAuthorsBooksPositiveResponse book : allBooks) {
            assertEquals(book.getBookTitle(), bookTitles.get(i));
            assertEquals(book.getAuthor().getId(), id);
            i++;
        }
    }

    public static void verifyBodyGetEmptyBookList(List<GetAllAuthorsBooksPositiveResponse> allBooks) {
        assertEquals(allBooks.size(), 0);
    }
}


