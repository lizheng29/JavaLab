package lizheng.model;

import io.searchbox.annotations.JestId;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{

	private static final long serialVersionUID = 238121906315878750L;

	@JestId
	private Long id;
	private String content;
	private Date postTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
	
}
