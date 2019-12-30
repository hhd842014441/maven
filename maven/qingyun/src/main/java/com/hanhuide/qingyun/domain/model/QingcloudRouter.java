package com.hanhuide.qingyun.domain.model;

import java.util.List;

public class QingcloudRouter {
	private String router_id;
	private String router_name;
	private String description;
	private Integer router_type;
	private String private_ip;
	private Integer is_applied;
	private String status;
	private String transition_status;
	private String create_time;
	private String status_time;
	private String security_group_id;
	private QingCloudEIP eip;
	private List<QingCloudVxnet> vxnets;
	public String getRouter_id() {
		return router_id;
	}
	public void setRouter_id(String router_id) {
		this.router_id = router_id;
	}
	public String getRouter_name() {
		return router_name;
	}
	public void setRouter_name(String router_name) {
		this.router_name = router_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRouter_type() {
		return router_type;
	}
	public void setRouter_type(Integer router_type) {
		this.router_type = router_type;
	}
	public String getPrivate_ip() {
		return private_ip;
	}
	public void setPrivate_ip(String private_ip) {
		this.private_ip = private_ip;
	}
	public Integer getIs_applied() {
		return is_applied;
	}
	public void setIs_applied(Integer is_applied) {
		this.is_applied = is_applied;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransition_status() {
		return transition_status;
	}
	public void setTransition_status(String transition_status) {
		this.transition_status = transition_status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getStatus_time() {
		return status_time;
	}
	public void setStatus_time(String status_time) {
		this.status_time = status_time;
	}
	public String getSecurity_group_id() {
		return security_group_id;
	}
	public void setSecurity_group_id(String security_group_id) {
		this.security_group_id = security_group_id;
	}
	public QingCloudEIP getEip() {
		return eip;
	}
	public void setEip(QingCloudEIP eip) {
		this.eip = eip;
	}
	public List<QingCloudVxnet> getVxnets() {
		return vxnets;
	}
	public void setVxnets(List<QingCloudVxnet> vxnets) {
		this.vxnets = vxnets;
	}
	@Override
	public String toString() {
		return "QingcloudRouter [router_id=" + router_id + ", router_name="
				+ router_name + ", description=" + description
				+ ", router_type=" + router_type + ", private_ip=" + private_ip
				+ ", is_applied=" + is_applied + ", status=" + status
				+ ", transition_status=" + transition_status + ", create_time="
				+ create_time + ", status_time=" + status_time
				+ ", security_group_id=" + security_group_id + ", eip=" + eip
				+ ", vxnets=" + vxnets + "]";
	}

}
