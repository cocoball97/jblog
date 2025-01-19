package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public PostVo getPost(String id, Long categoryId, Long postId) {
		if (categoryId == 0L) {
			categoryId = 1L;
	    }
		if(postId == 0L) {
			postId = 1L;
		}
		return postRepository.findPost(id, categoryId, postId);
	}

	public List<PostVo> getPostList(String id, Long categoryId) {
		if (categoryId == 0L) {
			categoryId = 1L;
	    }
		return postRepository.findPostList(id, categoryId);
	}

	public void insert(String id, String title, String categoryName, String contents) {
		postRepository.insert(id, title, categoryName, contents);
	}
}
