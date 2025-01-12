package jblog.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.service.UserService;
import jblog.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {
	private UserService userService;
	
	// 의존성 주입
	public LoginInterceptor(UserService userService){
		this.userService = userService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = userService.getUser(email, password);
		
		if(authUser == null) {
			request.setAttribute("email", email);
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
			
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authUser", authUser);		
		response.sendRedirect(request.getContextPath());

		// 컨트롤러 전달x
		return false;
	}

}
