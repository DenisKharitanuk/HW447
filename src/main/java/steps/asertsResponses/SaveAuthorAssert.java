package steps.asertsResponses;


import models.positive_responses.SaveNewAuthorPositiveResponse;

import static org.junit.jupiter.api.Assertions.*;

public class SaveAuthorAssert {
    public static void verifySaveAuthorResponse(SaveNewAuthorPositiveResponse author) {
        assertNotNull(author.getAuthorId());
        assertTrue(author.getAuthorId() > 0);
    }
}
