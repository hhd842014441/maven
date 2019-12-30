package com.hanhuide.qingyun.ui.model;

import java.util.List;


public class ApplySecurityGroupRequest extends Request {
	private String security_group;
    private List<String> instances;
    private String zone;

	public String getSecurity_group() {
		return security_group;
	}
	public void setSecurity_group(String security_group) {
		this.security_group = security_group;
	}
	public List<String> getInstances() {
		return instances;
	}
	public void setInstances(List<String> instances) {
		this.instances = instances;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}


}
