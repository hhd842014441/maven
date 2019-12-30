package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class CreateSnapshotsResponse {

	public static CreateSnapshotsResponse fromJson(String jsonCreateSnapshotsResponse){
		Gson gson = new Gson();
		CreateSnapshotsResponse createSnapshotsResponse = gson.fromJson(jsonCreateSnapshotsResponse, CreateSnapshotsResponse.class);
		return createSnapshotsResponse;
	}

}
