package entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.sql.Date;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @XmlElement(name = "id", required = true)
    @JsonProperty("id")
    private long id;

    @XmlElement(name = "book_title", required = true)
    @JsonProperty("bookTitle")
    private String bookTitle;

    @XmlElement(name = "author", required = true)
    @JsonProperty("author")
    private Author author;

    @XmlElement(name = "updated", required = false)
    @JsonProperty("updated")
    private Date updated;

}
