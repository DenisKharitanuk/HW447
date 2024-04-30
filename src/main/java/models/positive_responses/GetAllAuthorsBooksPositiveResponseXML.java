package models.positive_responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.*;
import lombok.Data;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllAuthorsBooksPositiveResponseXML {
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book", required = true)
    private List <GetAllAuthorsBooksPositiveResponse> book;
}
