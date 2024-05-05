package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveNewAuthorRequest {
    private String firstName;
    private String familyName;
    private String secondName;
    private String birthDate;
}
