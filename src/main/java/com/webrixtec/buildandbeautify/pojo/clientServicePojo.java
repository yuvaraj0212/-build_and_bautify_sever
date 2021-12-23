package com.webrixtec.buildandbeautify.pojo;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class clientServicePojo {

	
	private Long clientId;
	@NotEmpty(message="product name must not to be null")
	private String productName;
	@NotEmpty(message="service  must not to be null")
	private String service;
	@NotEmpty(message="bill No must not to be null")
	private String billno;
	@NotEmpty(message="comment must not to be null")
	private String comment;
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	@Transient
	private MultipartFile mfile;
	
	
}
