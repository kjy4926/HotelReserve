package kg.groupc.project.dto.account;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantScoreDto {
	private Long seq;
	private Long restaurantSeq;
	private String restaurantName;
	private Long score;
	private String scoreString;
	private String desc;
	private String writer;
	private Date day;
}
