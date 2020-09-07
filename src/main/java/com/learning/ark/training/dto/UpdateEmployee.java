package com.learning.ark.training.dto;

import java.io.Serializable;

import com.learning.ark.training.util.Utils;


/**
 * @author Ravi Kumar Annepu
 */
public class UpdateEmployee extends AddEmployee implements Serializable {

	private static final long serialVersionUID = 3409856094542412538L;

	private String id;

	public void sanitize() {
		super.sanitize();
		setId(Utils.nullOrTrimmed(id));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
