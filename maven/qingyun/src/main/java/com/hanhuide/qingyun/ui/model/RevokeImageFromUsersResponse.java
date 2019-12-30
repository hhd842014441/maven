package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class RevokeImageFromUsersResponse {

	private String action;
	private Integer ret_code;
	private String message;

	public static RevokeImageFromUsersResponse fromJson(String jsonDescribeImagesResponse){
		Gson gson = new Gson();
		RevokeImageFromUsersResponse describeImagesResponse = gson.fromJson(jsonDescribeImagesResponse, RevokeImageFromUsersResponse.class);
		return describeImagesResponse;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getRet_code() {
		return ret_code;
	}

	public void setRet_code(Integer ret_code) {
		this.ret_code = ret_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
