package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Author;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveNewAuthorRequest {
    private String firstName;
    private String familyName;
    private String secondName;
}
