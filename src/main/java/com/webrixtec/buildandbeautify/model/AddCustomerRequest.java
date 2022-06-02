package com.webrixtec.buildandbeautify.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_customer")
public class AddCustomerRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="customername must not to be null")
	private String customername;
	
//	@NotEmpty(message="type must not to be null")
	private String type;
	
	@NotEmpty(message="phone must not to be null")
	private String phone;
	
	@NotEmpty(message="handeld must not to be null")
	private String handeld;
	
	public String getHandeld() {
		return handeld;
	}
	public void setHandeld(String handeld) {
		this.handeld = handeld;
	}
//	@NotEmpty(message="email must not to be null")
	private String email;
	
	@NotEmpty(message="lead must not to be null")
	private String lead;
	
	@NotEmpty(message="category must not to be null")
	private ArrayList<String> category;
	
//	@NotEmpty(message="product must not to be null")
	private String product;
	
//	@NotEmpty(message="address must not to be null")
	private String address;
	
	@NotEmpty(message="quantity must not to be null")
	private String quantity;
	
	@NotEmpty(message="comment must not to be null")
	private String comment;
	
//	@NotEmpty(message="others must not to be null")
	private String others;
	@NotEmpty(message="Area must not to be null")
	private String area;
	@NotEmpty(message="pincode must not to be null")
	private String pincode;
	
	private String phone2;
	private Date createDate;
	private Date modifiedDate;
	
	
	
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
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

	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
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
	public AddCustomerRequest(Long id, @NotEmpty(message = "customername must not to be null") String customername,
			String type, @NotEmpty(message = "phone must not to be null") String phone,
			@NotEmpty(message = "email must not to be null") String email,
			@NotEmpty(message = "lead must not to be null") String lead,
			@NotEmpty(message = "category must not to be null") ArrayList<String> category, String product,
			String address, @NotEmpty(message = "quantity must not to be null") String quantity,
			@NotEmpty(message = "comment must not to be null") String comment, String others, String phone2,
			Date createDate, Date modifiedDate) {
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
		this.phone2 = phone2;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
	public AddCustomerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
