package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class DeleteSnapshotsResponse {

	public static DeleteSnapshotsResponse fromJson(String jsonDeleteSnapshotsResponse){
		Gson gson = new Gson();
		DeleteSnapshotsResponse deleteSnapshotsResponse = gson.fromJson(jsonDeleteSnapshotsResponse, DeleteSnapshotsResponse.class);
		return deleteSnapshotsResponse;
	}

}
