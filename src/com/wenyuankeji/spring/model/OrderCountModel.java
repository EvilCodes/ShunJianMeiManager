package com.wenyuankeji.spring.model;

import java.util.Date;

public class OrderCountModel {
	private int orderNumber;//预约订单
	private int prepaidNumer;//完成支付
	private int orderCompletedNumber;//完成预约
	private int serviceCompletedNumer;//完成服务
	private int exceptionNumber;//异常数目
	private double exceptionPercentage;//异常比例
	private Date orderDate;//日期
	
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getPrepaidNumer() {
		return prepaidNumer;
	}
	public void setPrepaidNumer(int prepaidNumer) {
		this.prepaidNumer = prepaidNumer;
	}
	public int getOrderCompletedNumber() {
		return orderCompletedNumber;
	}
	public void setOrderCompletedNumber(int orderCompletedNumber) {
		this.orderCompletedNumber = orderCompletedNumber;
	}
	public int getServiceCompletedNumer() {
		return serviceCompletedNumer;
	}
	public void setServiceCompletedNumer(int serviceCompletedNumer) {
		this.serviceCompletedNumer = serviceCompletedNumer;
	}
	public int getExceptionNumber() {
		return exceptionNumber;
	}
	public void setExceptionNumber(int exceptionNumber) {
		this.exceptionNumber = exceptionNumber;
	}
	public double getExceptionPercentage() {
		return exceptionPercentage;
	}
	public void setExceptionPercentage(double exceptionPercentage) {
		this.exceptionPercentage = exceptionPercentage;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
