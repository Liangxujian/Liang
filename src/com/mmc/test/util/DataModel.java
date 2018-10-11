package com.mmc.test.util;

public class DataModel {
	Object result;
	Object error;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public DataModel(Object result, Object error) {
		super();
		this.result = result;
		this.error = error;
	}

	public DataModel() {
		super();
	}

	@Override
	public String toString() {
		return "DataModel [result=" + result + ", error=" + error + "]";
	}
}