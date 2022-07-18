package kg.groupc.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMsg = exception.getMessage();
		System.out.println(errorMsg);
		String msg = errorMessageToKR(request, errorMsg);
		System.out.println("1234" + msg);
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("/login").forward(request, response);
	}
	private String errorMessageToKR(HttpServletRequest request, String errorMsg) {
		String msg = "";
		if (errorMsg.equals("Bad credentials")) {
			msg = "비밀번호를 확인 해주세요.";
		} else if (errorMsg.equals("User account has expired")) {
			msg = "라이센스가 만료 되었습니다.";
		} else if (errorMsg.equals("User account is locked")) {
			msg = "5회 이상의 잘못된 비밀번호 입력으로 계정이 잠김 처리 되었습니다.";
		} else if (errorMsg.equals("User is disabled")) {
			msg = "사용불가 된 계정 입니다.";
		} else if (errorMsg.equals("UserDetailsService returned null, which is an interface contract violation")) {
			msg = "사용자 계정 정보가 없습니다.\n아이디를 다시 확인 해주세요.";
		}
		return msg;
	}
}
