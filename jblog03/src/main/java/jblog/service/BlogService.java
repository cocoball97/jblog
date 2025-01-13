package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Service
public class BlogService {
	private BlogRepository blogrepository;

	public BlogService(BlogRepository blogrepository) {
		this.blogrepository = blogrepository;
	}

	public CategoryVo getCategory(String id) {
		return blogrepository.findCategory(id);
	}

	public PostVo getPost(String id) {
		return blogrepository.findPost(id);
	}



	

}
