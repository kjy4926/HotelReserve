package kg.groupc.project.dto.hotel;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomAddFormDto {
	private Long hotelSeq;
	private String name;
	private Long people;
	private Long price;
	private String description;
	private MultipartFile uploadFile;
}
