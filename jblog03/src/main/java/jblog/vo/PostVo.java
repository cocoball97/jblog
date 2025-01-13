package jblog.vo;

public class PostVo {
	private Long id;
	private String contents;
	private String reg_date;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private Long category_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
}
