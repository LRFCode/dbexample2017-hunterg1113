
package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "first",
    "last"
})
public class Name {

    @JsonProperty("title")
    private String title;
    @JsonProperty("first")
    private String first;
    @JsonProperty("last")
    private String last;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("first")
    public String getFirst() {
        String firstLetter = first.substring(0,1).toUpperCase();

        String name = first.substring(1);

        return firstLetter + name;
    }

    @JsonProperty("first")
    public void setFirst(String first) {
        this.first = first;
    }

    @JsonProperty("last")
    public String getLast() {
        String lastLetter = last.substring(0,1).toUpperCase();

        String name = last.substring(1);

        return lastLetter + name;
    }

    @JsonProperty("last")
    public void setLast(String last) {
        this.last = last;
    }

}
