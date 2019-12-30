package com.hanhuide.qingyun.ui.model;

import java.util.List;

public class StartLoadBalancersRequest extends Request {
	private List<String> loadbalancers;
	private String zone;

	public List<String> getLoadbalancers() {
		return loadbalancers;
	}

	public void setLoadbalancers(List<String> loadbalancers) {
		this.loadbalancers = loadbalancers;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

}
