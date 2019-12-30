package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class ModifyInstanceAttributesResponse {

	private String action;
	private Integer ret_code;
	private String message;

	public static ModifyInstanceAttributesResponse fromJson(String jsonModifyInstanceAttributesResponse){
		Gson gson = new Gson();
		ModifyInstanceAttributesResponse modifyInstanceAttributesResponse = gson.fromJson(jsonModifyInstanceAttributesResponse, ModifyInstanceAttributesResponse.class);
		return modifyInstanceAttributesResponse;
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
