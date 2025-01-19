package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<CategoryVo> findCategory(String id, Long categoryId) {
		return sqlSession.selectList("category.findCategory",Map.of("id", id, "categoryId", categoryId));
	}

	public List<CategoryVo> findCategoryList(String id) {
		return sqlSession.selectList("category.findCategoryList", id);
	}

	public void insert(String id, String name, String description) {
		sqlSession.insert("category.insert", Map.of("id",id, "name", name, "description", description));
	}

	public CategoryVo findCategoryOne(String id, Long categoryId) {
		System.out.println("repository categoryid: "+categoryId);
		return sqlSession.selectOne("category.findCategoryOne", Map.of("id", id, "categoryId", categoryId));
	}

	public void deleteCategory(Long category_id) {
		sqlSession.delete("category.deleteCategory", category_id);
	}

	public void initInsert(String id) {
		sqlSession.insert("category.initInsert",id);
	}

	public List<CategoryVo> defaultFindCategory(String id) {
		return sqlSession.selectOne("category.findCategoryOne", id);
	}

}
