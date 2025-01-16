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
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo authUser = userService.getUser(id, password);
		
		if(authUser == null) {
			request.setAttribute("id", id);
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
			
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authUser", authUser);		
		response.sendRedirect(request.getContextPath());
		//정상출력
		System.out.println("authUser id:"+authUser.getId());

		// 컨트롤러 전달x
		return false;
	}

}
