package com.example.demo.dto;

import lombok.Getter;

@Getter
public class ResultData<T> {
	private String resultCode;
	private String msg;
	private T data;
	private String extraData;
	
	public ResultData(String resultCode, String msg, T data) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultData(String resultCode, String msg, T data, String extraData) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.data = data;
		this.extraData = extraData;
	}
	
	
	public ResultData(String resultCode, String msg) {
		this.resultCode = resultCode;
		this.msg = msg;
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S");
	}
	
	
}
