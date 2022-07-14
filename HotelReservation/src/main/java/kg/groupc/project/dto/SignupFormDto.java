package kg.groupc.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupFormDto {
	@NotBlank(message = "아이디는 필수 입력사항입니다.")
	@Pattern(regexp = "^[a-z][a-z0-9]{7,15}$", message = "올바르지 않은 아이디입니다.")
	private String userId;
	
	@NotBlank(message = "비밀번호는 필수 입력사항입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{12,20}$$",
			message = "올바르지 않은 비밀번호입니다.")
	private String password;
	
	@NotBlank(message = "비밀번호 확인은 필수 입력사항입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{12,20}$$",
			message = "비밀번호 확인 입력이 잘못되었습니다.")
	private String passwordCheck;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String addressDetail;
	
	@NotBlank
	private String birth;
}
