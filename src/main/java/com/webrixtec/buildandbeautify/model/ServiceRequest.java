package com.webrixtec.buildandbeautify.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tbl_ServiceRequest")
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId", nullable = false, referencedColumnName = "id")
	private UserModel userModel;
	@NotEmpty(message="product name must not to be null")
	private String productName;
	@NotEmpty(message="service  must not to be null")
	private String service;
	@NotEmpty(message="bill No must not to be null")
	private String billno;
	@NotEmpty(message="comment must not to be null")
	private String comment;
	private String filename;
	private String imageURL;
//	private byte[] imageURL;
	
	
//	public byte[] getImageURL() {
//		return imageURL;
//	}
//	public void setImageURL(byte[] imageURL) {
//		this.imageURL = imageURL;
//	}
	@Transient
	private MultipartFile mfile;
	private Date createDate;
	private Date modifiedDate;

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
	
	
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	
	
	
	
}
