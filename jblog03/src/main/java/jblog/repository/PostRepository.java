package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
	private SqlSession sqlSession;
	
	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<PostVo> findPostList(String id, Long categoryId) {
		return sqlSession.selectList("post.findPostList", Map.of("id", id, "categoryId", categoryId));
	}
	
	public List<PostVo> findPost(String id, Long categoryId, Long postId) {
		return sqlSession.selectList("post.findPost", Map.of("id", id, "categoryId", categoryId, "postId", postId));
	}

}
