package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class ModifySnapshotAttributesResponse {

	public static ModifySnapshotAttributesResponse fromJson(String jsonModifySnapshotAttributesResponse){
		Gson gson = new Gson();
		ModifySnapshotAttributesResponse modifySnapshotAttributesResponse = gson.fromJson(jsonModifySnapshotAttributesResponse, ModifySnapshotAttributesResponse.class);
		return modifySnapshotAttributesResponse;
	}

}
