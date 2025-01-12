package jblog.vo;

public class CategoryVo {
	Long id;
	String name;
	String description;
	Long blog_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(Long blog_id) {
		this.blog_id = blog_id;
	}
}
