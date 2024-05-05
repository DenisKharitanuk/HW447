package post_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.negative_responses.NegativeResponses;
import models.positive_responses.SaveNewAuthorPositiveResponse;
import models.positive_responses.SaveNewBooksPositiveResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import steps.asertsResponses.SaveBookAssert;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static steps.Specifications.*;
import static steps.asertsResponses.NegativeAsser.verifyBodyNegative;
import static steps.DateGenerator.dateGenerator;

@Epic("PostTests")
@Story("saveNewBook")
public class SaveNewBooksTest {

    @DisplayName("Сохранение книги ")
    @Description("Книга сохранена , ответ содержит id сохраненной  книги , статус код 201")
    @Test
    public void saveBookTest() {
        SaveNewAuthorPositiveResponse author = requestSpecSaveNewAuthor(randomAlphabetic(5),
                randomAlphabetic(5), randomAlphabetic(5), 201, dateGenerator());
        long id = author.getAuthorId();
        String bookTitle = randomAlphabetic(5);
        SaveNewBooksPositiveResponse book = requestSpecSaveNewBook(bookTitle, id, 201);
        long bookId = book.getBookId();
        SaveBookAssert.verifyBodySaveBook(book, bookId);
    }


    @DisplayName("Сохранение книги с неизвестным id автора")
    @Description("Книга не сохранена , статус код 409, ошибка 1004")
    @Test
    public void saveBookUnknownAuthorTest() {
        NegativeResponses response = requestSpecSaveNewBookNegative(randomAlphabetic(5), 666, 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Сохранение книги с отрицательным id автора")
    @Description("Книга не сохранена , статус код 409, ошибка 1004")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(longs = {-1, -2,})
    public void saveBookNegativeId(long id) {
        NegativeResponses response = requestSpecSaveNewBookNegative(randomAlphabetic(5), id, 409);
        verifyBodyNegative(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Сохранение книги с пустым полем bookTitle")
    @Description("Книга не сохранена , статус код 400, ошибка 1001")
    @ParameterizedTest
    @NullSource
    public void saveBookNullTitle(String bookTitle) {
        NegativeResponses response = requestSpecSaveNewBookNegative(bookTitle, 1, 400);
        verifyBodyNegative(response, "Валидация не пройдена", "Не передан обязательный параметр: bookTitle");
    }
}

