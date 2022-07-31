package kg.groupc.project.dto.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
	private Long seq;
	private Long hotelSeq;
	private String name;
	private Long people;
	private Long price;
	private String description;
	private String img;
}