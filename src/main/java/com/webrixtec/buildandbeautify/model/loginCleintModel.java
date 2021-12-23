package com.webrixtec.buildandbeautify.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import antlr.collections.List;


@Entity
@Table(name="login_client_Model")
public class loginCleintModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "clientId", nullable = false, referencedColumnName = "id")
		private UserModel userModel;
		@NotEmpty(message="product Name must not to be null")
		private String product;
		@NotEmpty(message="comment must not to be null")
		private String comment;
		@NotEmpty(message="category must not to be null")
		private ArrayList<String> category;
		@NotEmpty(message="quantity must not to be null")
		private String quantity;
		private String other;
		private String product2;
		private Date createDate;
		private Date modifiedDate;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public UserModel getUserModel() {
			return userModel;
		}
		public void setUserModel(UserModel userModel) {
			this.userModel = userModel;
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
		public loginCleintModel() {
			super();
			this.id = id;
			this.userModel = userModel;
			this.product = product;
			this.comment = comment;
			this.category = category;
			this.quantity = quantity;
			this.other = other;
			this.product2 = product2;
			this.createDate = createDate;
			this.modifiedDate = modifiedDate;
		}
		
		
	
	
}
