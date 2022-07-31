package kg.groupc.project.dto.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
	private Long seq;
	private String name;
	private String phone;
	private String address;
	private String description;
	private String img;
}
