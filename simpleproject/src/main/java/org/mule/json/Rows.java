package org.mule.json;

public class Rows {
	
	private String textData;
	private String phoneNumber;
	private String response;
	
	public void setTextData(String textData) {
		this.textData = textData;
	}
	public String getTextData() {
		return textData;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	 @Override
	    public String toString() {
	        return textData + " - " + phoneNumber+"-"+response;
	    }
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}


}
