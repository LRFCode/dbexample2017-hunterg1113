
package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "results",
    "info"
})
public class RandomUser {

    @JsonProperty("results")
    private List<User> results = null;
    @JsonProperty("info")
    private Info info;

    @JsonProperty("results")
    public List<User> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<User> results) {
        this.results = results;
    }

    @JsonProperty("info")
    public Info getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(Info info) {
        this.info = info;
    }

}
