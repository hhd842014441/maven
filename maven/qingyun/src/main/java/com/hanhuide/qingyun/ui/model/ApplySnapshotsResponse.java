package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class ApplySnapshotsResponse {

	public static ApplySnapshotsResponse fromJson(String jsonApplySnapshotsResponse){
		Gson gson = new Gson();
		ApplySnapshotsResponse applySnapshotsResponse = gson.fromJson(jsonApplySnapshotsResponse, ApplySnapshotsResponse.class);
		return applySnapshotsResponse;
	}

}
