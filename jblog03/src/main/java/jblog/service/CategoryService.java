package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<CategoryVo> getCategory(String id, Long categoryId) {
		if (categoryId == 0L) {
			categoryId = 1L;
	    }
		return categoryRepository.findCategory(id, categoryId);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.findCategoryList(id);
	}

	
	public void insert(String id, String name, String description) {
		categoryRepository.insert(id, name, description);
	}

	public CategoryVo getCategoryOne(String id, Long categoryId) {
		if (categoryId == 0L) {
			categoryId = 1L;
	    }
		System.out.println("service categoryid: "+categoryId);
		return categoryRepository.findCategoryOne(id, categoryId);
	}

	public void deleteCategory(Long category_id) {
		categoryRepository.deleteCategory(category_id);
		
	}
}
