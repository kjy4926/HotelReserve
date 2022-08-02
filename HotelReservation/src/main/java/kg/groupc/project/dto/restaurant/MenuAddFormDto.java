package kg.groupc.project.dto.restaurant;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuAddFormDto {
	
	//restaurant의 pk값
	private Long restaurant;
	private String name;
	private Long price;
	private String description;
	private String img;
	
	private MultipartFile uploadFile;
}
