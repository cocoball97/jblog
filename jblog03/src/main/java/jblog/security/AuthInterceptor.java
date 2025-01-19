package jblog.security;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	// return 값이 true라면 진행, false라면 중지 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 1. Handler 종류 확인해서 default면 중지
		// HandlerMethod : 컨트롤러 메서드 처리
		if(!(handler instanceof HandlerMethod)) {
			// DefaultServletRequestHandler 타입인 경우
			// DefaultServletHandler가 처리하는 경우(정적자원, /assets/**, mapping이 안되어 있는 URL)
			System.out.println("Intercepted Request: " + request.getRequestURI());
			return true;
		}
		
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;	
		
	    // 3. Handler Method에서 @Auth 가져오기   
		// getMethodAnnotation : Method에서 어노테이션 찾기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		// 4. Handler Method에서 @Auth가 없으면 클래스(타입)에서 가져오기
		if(auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
	
		
		// 5. @Auth가 없다면 (인증이 필요없는 요청)
		if(auth == null) {
			System.out.println("5번");
			return true;
		}
		
		System.out.println("5-2번");
		// 6. @Auth가 붙어 있기 때문에 인증 (Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		// 로그인이 안 되어 있다면
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			System.out.println("6번");
			return false;
		}
		System.out.println("6-2번");
		
		// 7. 권한 체크를 위해 role(USER, ADMIN) 가져오기		
		// default(USER)가 있어 무조건 존재
		String role = auth.role();
		
		// 8. @Auth의 role이 "USER"인 경우, authUser 의 role은 상관없다
		if("user".equals(role)) {
			System.out.println("8번");
			return true;
		}
		System.out.println("8-2번");
		
		// 9. @Auth의 role이 "admin"이 아니거나 관리자와 접속자가 다른경우 
		String blog_Id = request.getRequestURI().split("/")[2];
//		System.out.println("authuser.id:"+authUser.getId());
//		System.out.println("blog_id출력:"+blog_Id);
		
		if(!"admin".equals(authUser.getRole())) {
			response.sendRedirect(request.getContextPath() + "/" + blog_Id);
			System.out.println("9번");
			return false;
		}
		 
		if(!authUser.getId().equals(blog_Id)) {
			response.sendRedirect(request.getContextPath() + "/" + blog_Id);
			System.out.println("9-2번");
			return false;
		}
		 
		System.out.println("10번");
		// 10. 옳은 관리자 권한  [@Auth(role="ADMIN") && authUser.role="ADMIN"]
		return true;
	}
	
	

}
