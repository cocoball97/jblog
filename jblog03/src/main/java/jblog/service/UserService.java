package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private CategoryRepository categoryRepository;

	
	public UserService(UserRepository userRepository,BlogRepository blogRepository, CategoryRepository categoryRepository,PostRepository postRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.categoryRepository = categoryRepository;
	}

	
	@Transactional
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		blogRepository.insert(userVo.getId());		
		categoryRepository.initInsert(userVo.getId());
		
//	    // 테스트
//		if (true) {
//	        throw new RuntimeException("Rollback Test");
//	    }
	}

	// id, pw 인증
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	// 아이디 존재 유무 확인
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
}
