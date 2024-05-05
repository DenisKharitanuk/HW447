package get_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

import models.negative_responses.NegativeResponses;
import models.positive_responses.GetAllAuthorsBooksPositiveResponse;
import models.positive_responses.SaveNewAuthorPositiveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static steps.DateGenerator.dateGenerator;
import static steps.Specifications.*;
import static steps.asertsResponses.GetAllBookAssert.*;
import static steps.asertsResponses.NegativeAsser.verifyBodyNegative;

@Story("Get all books")
@Epic("Get tests")
public class GetAllAuthorsBooksTest {

    @DisplayName("Получить все книги автора")
    @Description("Список всех книг автора в соответствии с id автора ,список состоит из 1 книги , статус код 201")
    @Test
    public void getAllAuthorsBookTest() {
        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201, dateGenerator());
        long id = author.getAuthorId();
        String bookTitle = randomAlphabetic(5);
        requestSpecSaveNewBook(bookTitle, id, 201);
        Date updated = new Date();
        List<GetAllAuthorsBooksPositiveResponse> allBooks = requestSpecGetAllBooksJSON(String.valueOf(id), 200);

        verifyBodyGetBook(allBooks, id, bookTitle, 0, updated);
    }

    @DisplayName("Получить все книги автора")
    @Description("Список книг - пуст, статус код 201")
    @Test
    public void getAllAuthorsBooksListIsEmptyTest() {

        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201, dateGenerator());
        long id = author.getAuthorId();
        requestSpecGetAllBooksJSON(String.valueOf(id), 200);
        List<GetAllAuthorsBooksPositiveResponse> allBooks = requestSpecGetAllBooksJSON(String.valueOf(id), 200);
        verifyBodyGetEmptyBookList(allBooks);
    }

    @DisplayName("Получить все книги автора")
    @Description("Список всех книг автора в соответствии с id автора ,список состоит из множества книг, статус код 201")
    @Test
    public void getAllAuthorsBooksTest() {

        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201, dateGenerator());
        long id = author.getAuthorId();
        List<String> bookTitlesList = new ArrayList<>();
        String bookTitle = randomAlphabetic(5);
        bookTitlesList.add(bookTitle);
        String bookTitle2 = randomAlphabetic(5);
        bookTitlesList.add(bookTitle2);
        String bookTitle3 = randomAlphabetic(5);
        bookTitlesList.add(bookTitle3);
        requestSpecSaveNewBook(bookTitle, id, 201);
        requestSpecSaveNewBook(bookTitle2, id, 201);
        requestSpecSaveNewBook(bookTitle3, id, 201);
        List<GetAllAuthorsBooksPositiveResponse> allBooks = requestSpecGetAllBooksJSON(String.valueOf(id), 200);
        verifyBodyGetBooks(allBooks, id, bookTitlesList);
    }

    @DisplayName("Список книг от неизвестного автора")
    @Description("Список книг отсутствует, статус код 409")
    @Test
    public void getAllBooksUnknownAuthorTest() {
        NegativeResponses response = requestSpecGetAllBookNegative("1553", 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Список книг с некорректным форматом id")
    @Description("Список книг отсутствует,статус код 400")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(strings = {"incorrectID", " ", "null"})
    public void getAllBooksIdWrongFormatTest(String id) {
        NegativeResponses response = requestSpecGetAllBookNegative(id, 400);
        verifyBodyNegative(response, "1001", "Некорректный обязательный параметр");

    }

    @DisplayName("Список книг с отрицательным id")
    @Description("Список книг отсутствует, статус код 409")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(strings = {"-1", "-2"})
    public void getAllBooksIdNegativeTest(String id) {
        NegativeResponses response = requestSpecGetAllBookNegative(id, 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Список книг без id")
    @Description("Список книг отсутствует, статус код 400 ")
    @Test
    public void getAllBooksIdNullTest() {
        NegativeResponses response = requestSpecGetAllBookNegativeIdNull(400);
        verifyBodyNegative(response, "1001", "Некорректный обязательный параметр");
    }
}
