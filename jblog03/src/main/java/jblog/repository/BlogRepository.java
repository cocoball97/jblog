package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public CategoryVo findCategory(String id) {
		return sqlSession.selectOne("blog.findCategory");
	}

	public PostVo findPost(String id) {
		return sqlSession.selectOne("blog.findPost");
	}
	
	

}
