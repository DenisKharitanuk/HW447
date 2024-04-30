package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.Author;
import jakarta.xml.bind.annotation.*;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "author")
public class GetAllAuthorsBooksRequestXML {

    @XmlElement(name = "author_id", required = true)
    private long authorId;
}
