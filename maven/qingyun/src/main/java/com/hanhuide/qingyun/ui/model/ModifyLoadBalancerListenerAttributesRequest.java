package com.hanhuide.qingyun.ui.model;

public class ModifyLoadBalancerListenerAttributesRequest extends Request {
	public String loadbalancer_listener;
	private String loadbalancer_listener_name;
	private String balance_mode;
	private String session_sticky;
	private Integer forwardfor;
	private String healthy_check_method;
	private String healthy_check_option;
	private String zone;

	public String getLoadbalancer_listener() {
		return loadbalancer_listener;
	}

	public void setLoadbalancer_listener(String loadbalancer_listener) {
		this.loadbalancer_listener = loadbalancer_listener;
	}

	public String getLoadbalancer_listener_name() {
		return loadbalancer_listener_name;
	}

	public void setLoadbalancer_listener_name(String loadbalancer_listener_name) {
		this.loadbalancer_listener_name = loadbalancer_listener_name;
	}

	public String getBalance_mode() {
		return balance_mode;
	}

	public void setBalance_mode(String balance_mode) {
		this.balance_mode = balance_mode;
	}

	public String getSession_sticky() {
		return session_sticky;
	}

	public void setSession_sticky(String session_sticky) {
		this.session_sticky = session_sticky;
	}

	public Integer getForwardfor() {
		return forwardfor;
	}

	public void setForwardfor(Integer forwardfor) {
		this.forwardfor = forwardfor;
	}

	public String getHealthy_check_method() {
		return healthy_check_method;
	}

	public void setHealthy_check_method(String healthy_check_method) {
		this.healthy_check_method = healthy_check_method;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getHealthy_check_option() {
		return healthy_check_option;
	}

	public void setHealthy_check_option(String healthy_check_option) {
		this.healthy_check_option = healthy_check_option;
	}
}
