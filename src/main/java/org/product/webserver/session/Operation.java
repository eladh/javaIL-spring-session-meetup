package org.product.webserver.session;

import java.io.Serializable;

/**
 *
 */
public class Operation implements Serializable{

	private String id;
	private String data;

	public Operation(String id, String data) {
		this.id = id;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}