package com.hanhuide.qingyun.ui.model;

import java.util.List;


public class ReleaseEipsRequest extends Request {
    private List<String> eips;
    private String zone;

	public List<String> getEips() {
		return eips;
	}
	public void setEips(List<String> eips) {
		this.eips = eips;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}



}
