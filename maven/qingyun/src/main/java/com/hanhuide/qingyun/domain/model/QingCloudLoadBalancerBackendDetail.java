package com.hanhuide.qingyun.domain.model;

public class QingCloudLoadBalancerBackendDetail {

	private String loadbalancer_backend_id;
	private String loadbalancer_backend_name;
	private String loadbalancer_listener_id;
	private String loadbalancer_id;
	private String resource_id;
	private String status;
	private String create_time;
	private Integer port;
	private Integer weight;
	public String getLoadbalancer_backend_id() {
		return loadbalancer_backend_id;
	}
	public void setLoadbalancer_backend_id(String loadbalancer_backend_id) {
		this.loadbalancer_backend_id = loadbalancer_backend_id;
	}
	public String getLoadbalancer_backend_name() {
		return loadbalancer_backend_name;
	}
	public void setLoadbalancer_backend_name(String loadbalancer_backend_name) {
		this.loadbalancer_backend_name = loadbalancer_backend_name;
	}
	public String getLoadbalancer_listener_id() {
		return loadbalancer_listener_id;
	}
	public void setLoadbalancer_listener_id(String loadbalancer_listener_id) {
		this.loadbalancer_listener_id = loadbalancer_listener_id;
	}
	public String getLoadbalancer_id() {
		return loadbalancer_id;
	}
	public void setLoadbalancer_id(String loadbalancer_id) {
		this.loadbalancer_id = loadbalancer_id;
	}
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
