package jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public BlogVo findTitleAndProfile(String id) {
		return sqlSession.selectOne("blog.findTitleAndProfile", id);
	}

	public void update(String blog_id, String title, String profileurl) {
		sqlSession.update("blog.update", Map.of("blog_id", blog_id,"title", title, "profileurl", profileurl));
	}
}
