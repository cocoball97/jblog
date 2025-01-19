package jblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.BlogService;
import jblog.vo.BlogVo;

public class BlogInterceptor implements HandlerInterceptor {
	private final BlogService blogService;

	public BlogInterceptor(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 처음이 아니면 서블릿 컨텍스트에서 가져오기
		BlogVo blogVo = (BlogVo)request.getServletContext().getAttribute("blogVo");
		// 처음 가져오면 서블릿 컨텍스트에 저장
		if(blogVo == null) {
			blogVo = blogService.find();
			request.getServletContext().setAttribute("blogVo", blogVo);
		}

		return true;
	}
}