package kg.groupc.project.dto.account;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelScoreDto {
	private Long seq;
	private Long hotelSeq;
	private String hotelName;
	private Long score;
	private String scoreString;
	private String desc;
	private String writer;
	private Date day;
}
