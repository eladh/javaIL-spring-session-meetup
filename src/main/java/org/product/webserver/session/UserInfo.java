package org.product.webserver.session;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Service
@SessionScope
public class UserInfo implements Serializable{

	private String name;
	private Integer id;
	private List<Operation> operations = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
}