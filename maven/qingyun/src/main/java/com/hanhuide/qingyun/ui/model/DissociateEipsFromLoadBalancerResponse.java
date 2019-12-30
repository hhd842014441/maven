package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class DissociateEipsFromLoadBalancerResponse {
	private String action;
	private String job_id;
	private Integer ret_code;
	private String message;
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
	public static DissociateEipsFromLoadBalancerResponse fromJson(String jsonDissociateEipsFromLoadBalancerResponse){
		Gson gson = new Gson();
		DissociateEipsFromLoadBalancerResponse dissociateEipsFromLoadBalancerResponse = gson.fromJson(jsonDissociateEipsFromLoadBalancerResponse, DissociateEipsFromLoadBalancerResponse.class);
		return dissociateEipsFromLoadBalancerResponse;
	}

}
