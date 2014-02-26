package org.mule.json;

import java.util.List;

import org.jetel.ctl.CTLEntryPoint;

public class ProcessFusionRows {
	
	
	private String phoneNumber;
	

	public void processRows(Fusion fusion) {
		
		List<String> list1 = fusion.getRows();
		

		
		for (String subList:list1){
			String temp[] = subList.split(",");
			this.phoneNumber=temp[1];
			this.phoneNumber.toString();
		}
		
        
    }

	@Override
	public String toString() {
		return "ProcessFusionRows  Ph No" + this.phoneNumber + "]";
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
