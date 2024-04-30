package post_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import models.negative_responses.NegativeResponses;
import models.positive_responses.GetAllAuthorsBooksPositiveResponse;
import models.positive_responses.SaveNewAuthorPositiveResponse;
import models.positive_responses.SaveNewBooksPositiveResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import steps.Specifications;
import steps.asertsResponses.GetAllBookAssert;
import steps.asertsResponses.SaveBookAssert;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static steps.Specifications.*;
import static steps.asertsResponses.GetAllBookAssert.verifyBodyGetBooks;
import static steps.asertsResponses.NegativeAsser.verifyBodyNegative;

@Epic("PostTests")
@Story("saveNewBook")
public class SaveNewBooksTest {
    @DisplayName("New book save")
    @Description("The book is saved, status code 201, the response returns the id of the saved book")
    @Test
    public void saveBookTest() {
        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201);
        long id = author.getAuthorId();
        String bookTitle = randomAlphabetic(5);
        SaveNewBooksPositiveResponse book = requestSpecSaveNewBook(bookTitle, id, 201);
        long bookId = book.getBookId();
        SaveBookAssert.verifyBodySaveBook(book, bookId);
    }


    @DisplayName("Saving a new book with an unknown author id")
    @Description("The book is not saved, status code 409, error 1004")
    @Test
    public void saveBookUnknownAuthorTest() {
        NegativeResponses response = requestSpecSaveNewBookNegative(randomAlphabetic(5), 666, 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Saving a new book with a negative id")
    @Description("The book is not saved, status code 409, error 1004")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(longs = {-1, -2,})
    public void saveBookNegativeId(long id) {
        NegativeResponses response = requestSpecSaveNewBookNegative(randomAlphabetic(5), id, 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Saving a new book with an empty bookTitle")
    @Description("The book is not saved, status code 400, error 1001")
    @ParameterizedTest
    @NullSource
    public void saveBookNullTitle(String bookTitle) {
        NegativeResponses response = requestSpecSaveNewBookNegative(bookTitle, 1, 400);
        verifyBodyNegative(response, "Валидация не пройдена", "Не передан обязательный параметр: bookTitle");
    }
}

