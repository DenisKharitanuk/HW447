package steps.asertsResponses;

import entity.Book;
import io.restassured.response.ValidatableResponse;
import models.positive_responses.GetAllAuthorsBooksPositiveResponse;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllBookAssert {
    public static void verifyBodyGetBook(List<GetAllAuthorsBooksPositiveResponse> allBooks, long id, String bookTitle) {
        Optional<GetAllAuthorsBooksPositiveResponse> firstResponseOptional = allBooks.stream().findFirst();
        GetAllAuthorsBooksPositiveResponse firstResponse = firstResponseOptional.get();
        assertEquals(firstResponse.getBookTitle(), bookTitle);
        assertEquals(firstResponse.getAuthor().getId(), id);
    }
    public static void verifyBodyGetBooks(List<GetAllAuthorsBooksPositiveResponse> allBooks, long id, String bookTitle) {
        Optional<GetAllAuthorsBooksPositiveResponse> firstResponseOptional = allBooks.stream().findFirst();
        GetAllAuthorsBooksPositiveResponse firstResponse = firstResponseOptional.get();
        assertEquals(firstResponse.getBookTitle(), bookTitle);
        assertEquals(firstResponse.getAuthor().getId(), id);
    }

}


