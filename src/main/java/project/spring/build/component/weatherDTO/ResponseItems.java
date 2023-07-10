package project.spring.build.component.weatherDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseItems {

    @JsonProperty("item")
    private List<ResponseItem> item;

	public List<ResponseItem> getItem() {
		return item;
	}

	public void setItem(List<ResponseItem> item) {
		this.item = item;
	}
}