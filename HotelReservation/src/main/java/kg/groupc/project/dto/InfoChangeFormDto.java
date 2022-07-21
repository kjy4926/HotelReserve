package kg.groupc.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoChangeFormDto {
	@NotBlank(message = "이름을 입력해주세요.")
	private String username;
	
	private String userId;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@NotBlank(message = "핸드폰 번호를 입력해주세요.")
	@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "핸드폰 번호 형식이 잘못되었습니다.")
	private String phone;
	
	@NotBlank(message = "주소를 검색해주세요.")
	private String address;
	
	@NotBlank(message = "세부 주소를 입력해주세요.")
	private String addressDetail;
}
