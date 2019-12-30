package com.hanhuide.qingyun.ui.model;

import java.util.List;

import com.google.gson.Gson;

public class CreateRoutersResponse {

	private String action;
	private String job_id;
	private List<String> routers;
	private Integer ret_code;
	private String message;

	public static CreateRoutersResponse fromJson(String jsonCreateRoutersResponse){
		Gson gson = new Gson();
		CreateRoutersResponse createRoutersResponse = gson.fromJson(jsonCreateRoutersResponse, CreateRoutersResponse.class);
		return createRoutersResponse;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public List<String> getRouters() {
		return routers;
	}

	public void setRouters(List<String> routers) {
		this.routers = routers;
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
