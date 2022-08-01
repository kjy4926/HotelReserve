package kg.groupc.project.dto.inquire;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireDto {
	private Long seq;
	private String writer;
	private String hotel;
	private Long hotelSeq;
	private String title;
	private String description;
	private Date day;
	private String category;
	private Long status;
}
