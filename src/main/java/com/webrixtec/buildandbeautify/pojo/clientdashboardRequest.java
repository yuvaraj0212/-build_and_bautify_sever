package com.webrixtec.buildandbeautify.pojo;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import antlr.collections.List;

public class clientdashboardRequest {
	
	
	private Long clientId;
	@NotEmpty(message="product Name must not to be null")
	private String product;
	@NotEmpty(message="comment must not to be null")
	private String comment;
	@NotEmpty(message="category must not to be null")
	private ArrayList<String> category;
	@NotEmpty(message="quantity must not to be null")
	private String quantity;
	private String other;
	private String refName;
	private String refNo;
	private String product2;
	private Date createDate;
	private Date modifiedDate;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getProduct2() {
		return product2;
	}
	public void setProduct2(String product2) {
		this.product2 = product2;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	
}
