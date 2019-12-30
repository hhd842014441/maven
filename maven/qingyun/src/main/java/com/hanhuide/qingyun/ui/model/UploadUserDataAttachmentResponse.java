package com.hanhuide.qingyun.ui.model;

import com.google.gson.Gson;

public class UploadUserDataAttachmentResponse {

	public static UploadUserDataAttachmentResponse fromJson(String jsonUploadUserDataAttachmentResponse){
		Gson gson = new Gson();
		UploadUserDataAttachmentResponse uploadUserDataAttachmentResponse = gson.fromJson(jsonUploadUserDataAttachmentResponse, UploadUserDataAttachmentResponse.class);
		return uploadUserDataAttachmentResponse;
	}

}
