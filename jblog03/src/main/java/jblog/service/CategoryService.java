package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<CategoryVo> getCategory(String blog_id) {
		return categoryRepository.findCategory(blog_id);
	}

}
