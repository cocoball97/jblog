package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.UserVo;
import mysite.security.Auth;
import mysite.security.AuthUser;

@Controller
// asset이라는 사용자가 들어오면 정적 자원 처리에 문제가 발생할 수 있음
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	private BlogService blogService;
	private CategoryService categoryService;
	private PostService postService;
	
	public BlogController(BlogService blogService, CategoryService categoryService,	PostService postService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
	}
	
	// 인터셉터에서 리퀘스트에 정보담기?
	@RequestMapping({"", "/{path01}", "/{path01}/{path02}"})
	public String index(
		@PathVariable("id") String id,
		// Optional : null 값이 들어와도 예외발생하지 않음
		@PathVariable("path01") Optional<Long> path1,
		@PathVariable("path02") Optional<Long> path2,
		Model model){
		
		Long categoryId = 0L;
		Long postId = 0L;
		
		// postId, categoryId 모두 존재하는 경우
		// 특정게시물 존재
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
//			System.out.println("====== 첫번째 categoryId : "+categoryId);
//			System.out.println("====== 첫번쨰 postId : "+postId);
		} 
		// categoryId만 존재하는 경우
		// 리스트 
		else if(path1.isPresent()) {
			categoryId = path1.get();
//			System.out.println("====== 두번째 categoryId : "+categoryId);
		}
		// 둘다 없으면 진행
//		System.out.println("====== 마지막 categoryId : "+categoryId);
//		System.out.println("====== 마지막 postId : "+postId);
		
		// 서비스에서 구현 (비즈니스라서 서비스에서 하느건가??)  status?
		// categoryid == 0L -> default categoryId 결정
		// postId == 0L -> default postId 결정
		
		model.addAttribute("blogvo", blogService.getTitle(id));
        model.addAttribute("categoryvo", categoryService.getCategory(id, categoryId));
        // 단일 글
        model.addAttribute("postvo", postService.getPost(id, categoryId, postId));
        // 아래 리스트
        model.addAttribute("postlistvo", postService.getPostList(id, categoryId));
        
        // postvo 인덱스 첫번쨰 값 했는데 이게 방법이 맞나...  당연히 아니네 순서가 날짜순이잖니 
        model.addAttribute("categoryvoindex", categoryId);
        model.addAttribute("postvoindex", postId);
		
		System.out.println("blogcontroller.main(" + id + ", " + categoryId + ", " + postId +")" );		
		
		return "blog/blog-main";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping({"/admin","/admin/default"})
	public String adminDefault(
		@AuthUser UserVo authUser,
		Model model) {
		System.out.println();
		
		model.addAttribute("blogvo", blogService.getTitle(authUser.getId()));
		// 이거 jblog03 나온다
		System.out.println(authUser.getId());
		return "blog/admin-default";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/admin/category")
	public String adminCategory(
		@PathVariable("id") String id) {
		return "blog/admin-category";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/admin/write")
	public String adminWrite(
		@PathVariable("id") String id) {
		return "blog/admin-write";
	}

	
}