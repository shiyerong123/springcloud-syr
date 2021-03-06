package com.jk.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree implements Serializable {
	/**
	 * serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5110015530439064016L;

	private String id;

	private String pId; //父节点id

	private String name;

	private String state;

	private String hrefURL;

	private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性

	private List<MenuTree> children; //子节点数据

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHrefURL() {
		return hrefURL;
	}

	public void setHref(String href) {
		this.hrefURL = href;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
}