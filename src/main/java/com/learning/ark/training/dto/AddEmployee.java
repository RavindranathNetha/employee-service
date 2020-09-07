package com.learning.ark.training.dto;

import java.io.Serializable;

import com.learning.ark.training.util.Utils;

import lombok.Data;

/**
 * @author Ravi Kumar Annepu
 */
@Data
public class AddEmployee implements Serializable {

	private static final long serialVersionUID = -872027178830757167L;

	private String name;

	private String description;

	public void sanitize() {
		setName(Utils.nullOrTrimmed(name));
		setDescription(Utils.nullOrTrimmed(description));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
