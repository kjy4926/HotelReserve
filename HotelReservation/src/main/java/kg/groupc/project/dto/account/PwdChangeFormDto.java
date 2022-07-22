package kg.groupc.project.dto.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PwdChangeFormDto {
	@NotBlank(message = "비밀번호는 필수 입력사항입니다.")
	@Pattern(regexp = "^^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{12,20}$", message = "올바르지 않은 비밀번호입니다. 다시 입력해주세요.")
	private String password;
	
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{12,20}$$",
			message = "비밀번호 확인이 잘못되었습니다. 다시 입력해주세요.")
	private String passwordCheck;
}
