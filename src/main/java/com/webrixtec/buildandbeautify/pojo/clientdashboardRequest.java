package com.webrixtec.buildandbeautify.pojo;

import javax.validation.constraints.NotEmpty;

public class clientdashboardRequest {
	
	
	private Long clientId;
	@NotEmpty(message="Complaint must not to be null")
	private String complaint;
	@NotEmpty(message="comment must not to be null")
	private String comment;
	@NotEmpty(message="category must not to be null")
	private String category;
	@NotEmpty(message="product must not to be null")
	private String product;
	@NotEmpty(message="quantity must not to be null")
	private String quantity;
	@NotEmpty(message="other must not to be null")
	private String other;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
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
	
}
