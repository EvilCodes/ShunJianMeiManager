package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Entity
@Table(name = "userinfo")
public class UserinfoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String username;
	private String password;
	private String nickname;
	private int sex;
	private Date birthday;
	private int provinceid;
	private int cityid;
	private String bindphone;
	private int usertype;
	private int userstate;
	private Date lastlogintime;
	private Date createtime;
	private Date updatetime;
	private String referee;
	
	@Transient
	private String picturepath;
	
	@Transient
	private String dateFrom;
	@Transient
	private String dateTo;

	public int getUserid() {
		return userid;
	}

	@Column(name = "username")
	public String getUsername() {
		if (ShunJianMeiUtil.checkNull(username)) {
			return "";
		}
		return username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "nickname")
	public String getNickname() {
		if (ShunJianMeiUtil.checkNull(nickname)) {
			return "";
		}
		return nickname;
	}

	@Column(name = "sex")
	public int getSex() {
		return sex;
	}

	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}

	@Column(name = "provinceid")
	public int getProvinceid() {
		return provinceid;
	}

	@Column(name = "cityid")
	public int getCityid() {
		return cityid;
	}

	@Column(name = "bindphone")
	public String getBindphone() {
		if (ShunJianMeiUtil.checkNull(bindphone)) {
			return "";
		}
		return bindphone;
	}

	@Column(name = "usertype")
	public int getUsertype() {
		return usertype;
	}

	@Column(name = "userstate")
	public int getUserstate() {
		return userstate;
	}

	@Column(name = "lastlogintime")
	public Date getLastlogintime() {
		return lastlogintime;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	@Column(name = "updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}
	
	@Column(name = "referee")
	public String getReferee() {
		return referee;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public void setBindphone(String bindphone) {
		this.bindphone = bindphone;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public void setUserstate(int userstate) {
		this.userstate = userstate;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

}
