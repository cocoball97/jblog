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


	public BlogVo getTitleAndProfile(String id) {
		return blogrepository.findTitleAndProfile(id);
	}


	public void updateBlog(String id, String title, String profileURL) {
		blogrepository.update(id, title, profileURL);
		
	}
}
