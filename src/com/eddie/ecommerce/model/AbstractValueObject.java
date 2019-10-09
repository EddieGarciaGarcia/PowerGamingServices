package com.eddie.ecommerce.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public abstract class AbstractValueObject implements Serializable {
	
	public String toString() { return ToStringBuilder.reflectionToString(this) ;}
}
