package kg.groupc.project.dto.hotel;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelAddFormDto {
	private String name;
	private String phone;
	private String address;
	private String addressDetail;
	private String description;
	private MultipartFile uploadFile;
}
