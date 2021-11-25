package com.webrixtec.buildandbeautify.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="client_table")
public class clientModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="customername must not to be null")
	private String customername;
	@NotEmpty(message="type must not to be null")
	private String type;
	@NotEmpty(message="phone must not to be null")
	private String phone;
	@NotEmpty(message="email must not to be null")
	private String email;
	@NotEmpty(message="lead must not to be null")
	private String lead;
	@NotEmpty(message="category must not to be null")
	private String category;
	@NotEmpty(message="product must not to be null")
	private String product;
	@NotEmpty(message="address must not to be null")
	private String address;
	@NotEmpty(message="quantity must not to be null")
	private String quantity;
	@NotEmpty(message="comment must not to be null")
	private String comment;
	@NotEmpty(message="others must not to be null")
	private String others;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public clientModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public clientModel(Long id, @NotEmpty(message = "customername must not to be null") String customername,
			@NotEmpty(message = "type must not to be null") String type,
			@NotEmpty(message = "phone must not to be null") String phone,
			@NotEmpty(message = "email must not to be null") String email,
			@NotEmpty(message = "lead must not to be null") String lead,
			@NotEmpty(message = "category must not to be null") String category,
			@NotEmpty(message = "product must not to be null") String product,
			@NotEmpty(message = "address must not to be null") String address,
			@NotEmpty(message = "quantity must not to be null") String quantity,
			@NotEmpty(message = "comment must not to be null") String comment,
			@NotEmpty(message = "others must not to be null") String others) {
		super();
		this.id = id;
		this.customername = customername;
		this.type = type;
		this.phone = phone;
		this.email = email;
		this.lead = lead;
		this.category = category;
		this.product = product;
		this.address = address;
		this.quantity = quantity;
		this.comment = comment;
		this.others = others;
	}
	

}
