package com.hanhuide.qingyun.ui.model;

import java.util.List;

public class DeleteVxnetsRequest extends Request {
	private List<String> vxnets;
	private String zone;

	public List<String> getVxnets() {
		return vxnets;
	}

	public void setVxnets(List<String> vxnets) {
		this.vxnets = vxnets;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

}
