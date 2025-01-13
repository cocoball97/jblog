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

	public List<PostVo> getPost(String blod_id) {
		return postRepository.findPost(blod_id);
	}

}