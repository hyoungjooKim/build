package project.spring.build.component;

import java.util.Date;

import lombok.Data;

@Data
public class ConstructDTO {
	private Date date;
	private String text;
	public ConstructDTO(Date date, String text) {
		this.date = date;
		this.text = text;
	}
}
