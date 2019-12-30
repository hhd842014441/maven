package com.hanhuide.qingyun.ui.model;

import java.util.List;

public class LeaveVxnetRequest extends Request {
	private String vxnet;
	private List<String> instances;
	private String zone;

	public String getVxnet() {
		return vxnet;
	}

	public void setVxnet(String vxnet) {
		this.vxnet = vxnet;
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
