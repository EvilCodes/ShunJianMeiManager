package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_admininfo")
public class BaseAdmininfoModel {

	@Id
	private int adminid;
	private String username;
	private String password;
	private int roleid;
	private int storeid;
	private String truename;
	private String mobile;
	private String remark;
	private int state;
	private Date createtime;

	public int getAdminid() {
		return adminid;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "roleid")
	public int getRoleid() {
		return roleid;
	}

	@Column(name = "storeid")
	public int getStoreid() {
		return storeid;
	}

	@Column(name = "truename")
	public String getTruename() {
		return truename;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	@Column(name = "state")
	public int getState() {
		return state;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
