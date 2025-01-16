package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

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

	public Object getCategoryList(String id) {
		return categoryRepository.findCategoryList(id);
	}

}
