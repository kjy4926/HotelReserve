package kg.groupc.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMsg = getErrorMessage(exception);
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("/login").forward(request, response);
	}
	private String getErrorMessage(Exception exception) {
		String msg = "";
		if (exception instanceof BadCredentialsException) {
			msg = "아이디 혹은 비밀번호를 확인해주세요.";
		}else if(exception instanceof UsernameNotFoundException) {
			msg = "존재하지 않는 계정입니다.";
		}else if(exception instanceof AccountExpiredException) {
			msg = "라이센스가 만료된 계정입니다.";
		}else if(exception instanceof DisabledException) {
			msg = "사용불가 된 계정입니다.";
		}else if(exception instanceof LockedException) {
			msg = "잠금 처리된 계정입니다.";
		}else {
			msg = "알 수 없는 이유로 로그인이 실패하였습니다.\n 다시 시도해주세요.";
		}
		return msg;
	}
}
