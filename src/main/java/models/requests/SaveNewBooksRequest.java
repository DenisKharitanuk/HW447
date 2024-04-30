package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveNewBooksRequest {
    private String bookTitle;
    private Author author;
}
