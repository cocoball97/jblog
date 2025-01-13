package jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	private BlogService blogService;
	private CategoryService categoryService;
	private PostService postService;
	

	public BlogController(BlogService blogService, CategoryService categoryService, PostService postService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
	}
	
	@GetMapping("/{id}")
	public String blogmain(@PathVariable("id") String id, Model model){
		model.addAttribute("categoryvo", categoryService.getCategory(id));
		model.addAttribute("postvo", postService.getPost(id));
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin-default")
	public String admindefault(Model model){
		
		return "blog/admin-default";
	}
}