package get_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import models.negative_responses.NegativeResponses;
import models.positive_responses.GetAllAuthorsBooksPositiveResponse;
import models.positive_responses.SaveNewAuthorPositiveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.DateGenerator;
import steps.Specifications;
import steps.asertsResponses.GetAllBookAssert;

import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static steps.DateGenerator.dateGenerator;
import static steps.Specifications.*;
import static steps.asertsResponses.GetAllBookAssert.verifyBodyGetBook;
import static steps.asertsResponses.GetAllBookAssert.verifyBodyGetBooks;
import static steps.asertsResponses.NegativeAsser.verifyBodyNegative;

@Story("Get all books")
@Epic("Get tests")
public class GetAllAuthorsBooksTest {

    @DisplayName("Получить все книги автора")
    @Description("Список всех книг автора в соответствии с id автора ,список состоит из 1 созданой книги , статус код 201")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(longs = {10})
    public void getAllAuthorsBookTest() {

        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201, dateGenerator());
        long id = author.getAuthorId();
        String bookTitle = randomAlphabetic(5);
        requestSpecSaveNewBook(bookTitle, id, 201);

        List<GetAllAuthorsBooksPositiveResponse> allBooks = requestSpecGetAllBooksJSON(String.valueOf(id), 200);
        verifyBodyGetBook(allBooks, id, bookTitle);
    }

    @DisplayName("Получить все книги автора")
    @Description("Список всех книг автора в соответствии с id автора ,список состоит из множества книг, статус код 201")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(longs = {10})
    public void getAllAuthorsBooksTest() {

        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201, dateGenerator());
        long id = author.getAuthorId();
        String bookTitle = randomAlphabetic(5);
        String bookTitle2 = randomAlphabetic(5);
        String bookTitle3 = randomAlphabetic(5);
        requestSpecSaveNewBook(bookTitle, id, 201);
        requestSpecSaveNewBook(bookTitle2, id, 201);
        requestSpecSaveNewBook(bookTitle3, id, 201);

        List<GetAllAuthorsBooksPositiveResponse> allBooks = requestSpecGetAllBooksJSON(String.valueOf(id), 200);
        verifyBodyGetBooks(allBooks, id, bookTitle);
    }

    @DisplayName("Get a list of books by an unknown author")
    @Description("There is no list of books in the response, status code 409")
    @Test
    public void getAllBooksUnknownAuthorTest() {
        NegativeResponses response = requestSpecGetAllBookNegative("1553", 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Get a list of books , id is specified in incorrect format")
    @Description("There is no list of books in the response, status code 400")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(strings = {"incorrectID", " ", "null"})
    public void getAllBooksIdWrongFormatTest(String id) {
        NegativeResponses response = requestSpecGetAllBookNegative(id, 400);
        verifyBodyNegative(response, "1001", "Некорректный обязательный параметр");

    }

    @DisplayName("Get a list of all books with negative id")
    @Description("Book list is not exist, status code 409")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(strings = {"-1", "-2"})
    public void getAllBooksIdNegativeTest(String id) {
        NegativeResponses response = requestSpecGetAllBookNegative(id, 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Get a list of all books without  id")
    @Description("Book list is not exist, status code 400")
    @Test
    public void getAllBooksIdNullTest() {
        NegativeResponses response = requestSpecGetAllBookNegativeNull(400);
        verifyBodyNegative(response, "1001", "Некорректный обязательный параметр");
    }
}
