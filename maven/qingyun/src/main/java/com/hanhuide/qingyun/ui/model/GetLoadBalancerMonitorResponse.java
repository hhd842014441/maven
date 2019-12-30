package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class GetLoadBalancerMonitorResponse {

	public static GetLoadBalancerMonitorResponse fromJson(String jsonGetLoadBalancerMonitorResponse){
		Gson gson = new Gson();
		GetLoadBalancerMonitorResponse getLoadBalancerMonitorResponse = gson.fromJson(jsonGetLoadBalancerMonitorResponse, GetLoadBalancerMonitorResponse.class);
		return getLoadBalancerMonitorResponse;
	}

}
