package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
	private SqlSession sqlSession;
	
	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<PostVo> findPost(String blod_id) {
		return sqlSession.selectList("post.findPost", blod_id);
	}

}
