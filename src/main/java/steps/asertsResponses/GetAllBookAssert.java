package steps.asertsResponses;

import models.positive_responses.GetAllAuthorsBooksPositiveResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetAllBookAssert {
    public static void verifyBodyGetBook(List<GetAllAuthorsBooksPositiveResponse> allBooks, long id, String bookTitle, int bookIndex, Date updated) {

        assertEquals(allBooks.get(bookIndex).getBookTitle(), bookTitle);
        assertEquals(allBooks.get(bookIndex).getAuthor().getId(), id);
        Date date = allBooks.get(bookIndex).getUpdated();

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        assertEquals(format.format(updated), format.format(date));
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


