package models.positive_responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Author;
import entity.Book;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.requests.SaveNewBooksRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllAuthorsBooksPositiveResponse {
    @XmlElement(name = "book_title", required = true)
    @JsonProperty("bookTitle")
    private String bookTitle;


    @XmlElement(name = "updated", required = true)
    @JsonProperty("updated")
    private Date updated;

    @XmlElement(name = "author", required = true)
    @JsonProperty("author")
    private Author author;
}
