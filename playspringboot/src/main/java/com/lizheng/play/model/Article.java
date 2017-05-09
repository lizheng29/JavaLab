package com.lizheng.play.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

//@Document(indexName="article_index",type="article",shards=5,replicas=1,indexStoreType="fs",refreshInterval="-1")
public class Article  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1323000451084457280L;
	@Id
	private Long id;
	/**标题*/
	private String title;
	/**摘要*/
	private String abstracts;
	/**内容*/
	private String content;
	/**发表时间*/
	//@Field(format=DateFormat.date_time,index=FieldIndex.no,store=true,type=FieldType.Object)
	private Date postTime;
	/**点击率*/
	private Long clickCount;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
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
	public Long getClickCount() {
		return clickCount;
	}
	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}
	
	
}
