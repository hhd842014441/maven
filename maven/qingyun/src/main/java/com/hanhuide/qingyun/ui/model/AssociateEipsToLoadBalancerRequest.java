package com.hanhuide.qingyun.ui.model;

import java.util.List;


public class AssociateEipsToLoadBalancerRequest extends Request {
	private List<String> eips;
	private String loadbalancer;
	private String zone;
	public List<String> getEips() {
		return eips;
	}
	public void setEips(List<String> eips) {
		this.eips = eips;
	}
	public String getLoadbalancer() {
		return loadbalancer;
	}
	public void setLoadbalancer(String loadbalancer) {
		this.loadbalancer = loadbalancer;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
}
