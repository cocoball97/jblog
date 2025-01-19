package jblog.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jblog.dto.JsonResult;
import jblog.service.BlogService;

@RestController("blogApiController")
@RequestMapping("/api")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/admin/category/delete/{id}")
	public JsonResult deleteCategory(@PathVariable("id") Long id) {
		boolean isDeleted = blogService.deleteCategory(id);
		return JsonResult.success(Map.of("isDeleted", isDeleted));
	}
}