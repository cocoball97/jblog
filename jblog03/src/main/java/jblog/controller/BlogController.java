package jblog.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jblog.security.AuthUser;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;

@Controller
// asset이라는 사용자가 들어오면 정적 자원 처리에 문제가 발생할 수 있음
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	private final BlogService blogService;
	private final CategoryService categoryService;
	private final PostService postService;
	private final FileUploadService fileUploadService;
	private final ServletContext servletContext;
	private final ApplicationContext applicationContext;
	
	public BlogController(BlogService blogService, CategoryService categoryService,	PostService postService, FileUploadService fileUploadService, ServletContext servletContext, ApplicationContext applicationContext) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
		this.fileUploadService = fileUploadService;
		this.servletContext = servletContext;
		this.applicationContext = applicationContext;
	}
	
	// 인터셉터에서 리퀘스트에 정보담기?
	@RequestMapping({"", "/{path01}", "/{path01}/{path02}"})
	public String index(
		@PathVariable("id") String id,
		// Optional : null 값이 들어와도 예외발생하지 않음
		@PathVariable("path01") Optional<Long> path1,
		@PathVariable("path02") Optional<Long> path2,
		@AuthUser UserVo authUser,
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
		// categoryId만 존재하는 경우a
		// 리스트 
		else if(path1.isPresent()) {
			categoryId = path1.get();
//			System.out.println("====== 두번째 categoryId : "+categoryId);
		}
		
		// 서비스에서 구현 (비즈니스라서 서비스에서 하느건가??)  status?
		// categoryid == 0L -> default categoryId 결정
		// postId == 0L -> default postId 결정
		
		model.addAttribute("blogvo", blogService.getTitleAndProfile(id));
        model.addAttribute("categoryvo", categoryService.getCategory(id, categoryId));
        
        // 단일 글 - 카테고리 없다면 1번, post글 없다면 1번 글 노출(날짜순 역순 정렬되어 있음)
        model.addAttribute("postvo", postService.getPost(id, categoryId, postId));
        // 아래 리스트
        model.addAttribute("postlistvo", postService.getPostList(id, categoryId));
        
        // postvo 인덱스 첫번쨰 값 했는데 이게 방법이 맞나...  당연히 아니네 순서가 날짜순이잖니 
        model.addAttribute("categoryvoindex", categoryId);
        model.addAttribute("postvoindex", postId);
		
		System.out.println("blogcontroller.main(" + id + ", " + categoryId + ", " + postId +")" );		
		
		return "blog/main";
	}
	
	
//	@Auth(role="ADMIN")
//	보안으로 인해 경로 방해 받는다... 접속이 안됨
	
	@GetMapping("/admin")
	public String adminDefault(
		@AuthUser UserVo authUser,
		Model model) {
		
		model.addAttribute("blogvo", blogService.getTitleAndProfile(authUser.getId()));

		return "blog/admin-default";
	}
	
//	@Auth(role="ADMIN")
	@PostMapping("/admin")
	public String update(@AuthUser UserVo authUser,
			   @PathVariable("id") String id,
			   @RequestParam("logo-file") MultipartFile multipartFile,
			   @RequestParam("title") String title,
			   @RequestParam("profile") String profile) {
		
		BlogVo blogVo = new BlogVo();
		blogVo.setTitle(title);
		
		// 주소값 반환
		String profileURL = fileUploadService.restore(multipartFile);
		
		if(profileURL != null) {
			blogVo.setProfile(profileURL);
		}
		
		blogService.updateBlog(id, title,profileURL);
		
//		// 전체 영역에서 사용 가능 (이미지 전체범위에서 써야지)
//		// update "servlet context" bean
//		servletContext.setAttribute("blogVo", blogVo);
//		System.out.println("blogVo값 : "+blogVo);
//		
//		// update "application context" bean
//		BlogVo blog = applicationContext.getBean(BlogVo.class);
//		// blogVo -> blog 복사하여 데이터 동기화. 데이터 업데이트인듯?
//		BeanUtils.copyProperties(blogVo, blog);
		
		return "redirect:/" + authUser.getId() + "/admin";
	}
	
	
//	@Auth(role="ADMIN")
	@GetMapping("/admin/write")
	public String adminWrite(@AuthUser UserVo authUser, Model model) {
		model.addAttribute("blogvo", blogService.getTitleAndProfile(authUser.getId()));
		model.addAttribute("categoryvo", categoryService.getCategoryList(authUser.getId()));
		return "blog/admin-write";
	}
	
	@PostMapping("/admin/write")
	public String adminWrite(@AuthUser UserVo authUser,
		   @RequestParam("title") String title,
		   @RequestParam("category") String categoryName,
		   @RequestParam("contents") String contents) {
		
		postService.insert(authUser.getId(), title, categoryName, contents);
		return "redirect:/" + authUser.getId() + "/admin/write";
	}
	
	@GetMapping("/admin/category")
	public String adminCategory(@AuthUser UserVo authUser, Model model) {
		// application 범위에서 저장이 안됨
//		BlogVo blogVo = (BlogVo) servletContext.getAttribute("blogVo");
//		model.addAttribute("blogVo", blogVo);
		
		model.addAttribute("blogvo", blogService.getTitleAndProfile(authUser.getId()));
		model.addAttribute("categoryvo", categoryService.getCategoryList(authUser.getId()));
		return "blog/admin-category";
	}
	
	@PostMapping("/admin/category")
	public String adminCategory(@AuthUser UserVo authUser,
			@RequestParam("name") String name,
		    @RequestParam("desc") String desc) {
		
		categoryService.insert(authUser.getId(), name, desc);
		return "redirect:/" + authUser.getId() + "/admin/category";
	}

	
}