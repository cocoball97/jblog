package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;

@Service
public class BlogService {
	private BlogRepository blogrepository;

	public BlogService(BlogRepository blogrepository) {
		this.blogrepository = blogrepository;
	}


	public BlogVo getTitle(String id) {
		return blogrepository.findTitle(id);
	}
}
