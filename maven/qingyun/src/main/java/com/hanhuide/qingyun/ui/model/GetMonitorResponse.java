package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class GetMonitorResponse {

	public static GetMonitorResponse fromJson(String jsonGetMonitorResponse){
		Gson gson = new Gson();
		GetMonitorResponse getMonitorResponse = gson.fromJson(jsonGetMonitorResponse, GetMonitorResponse.class);
		return getMonitorResponse;
	}

}
