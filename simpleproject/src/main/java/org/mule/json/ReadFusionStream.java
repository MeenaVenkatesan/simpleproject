package org.mule.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;



public class ReadFusionStream {

	public static final String ACCOUNT_SID = "AC5da4c2f33a687a10f0a4a6d96814d134";
	public static final String AUTH_TOKEN = "7e4b486dc0987ea55531bd448e971f87";
	
	public List<Rows> readStream(Object obj) throws TwilioRestException{
		JsonReader reader = new JsonReader(new InputStreamReader((InputStream) obj));
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObj = jsonParser.parse(reader).getAsJsonObject();
		JsonArray rowsData = jsonObj.getAsJsonArray("rows");

		String[] strArray;
		String stTemp;
		Rows rows;
		List<Rows> dataRows = new ArrayList<Rows>();
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		Call call;
		CallFactory callFactory;
		
		for (JsonElement rowElement : rowsData) {
			rows = new Rows();
			stTemp = rowElement.toString();
			stTemp = stTemp.replace("[", "");
			stTemp = stTemp.replace("]", "");
			strArray = stTemp.split(",");
			rows.setTextData(strArray[0]);
			rows.setPhoneNumber(strArray[1]);
			
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("To", rows.getPhoneNumber()));
			params.add(new BasicNameValuePair("From", "+18589055732"));
			// Can be replaced with rows.getMessage() for upgraded twilio
			// account
			params.add(new BasicNameValuePair("Url",
					"http://demo.twilio.com/docs/voice.xml"));
			params.add(new BasicNameValuePair("Method", "GET"));
			params.add(new BasicNameValuePair("FallbackMethod", "GET"));
			params.add(new BasicNameValuePair("StatusCallbackMethod", "GET"));
			params.add(new BasicNameValuePair("Record", "false"));
			
			
			/*Map map = new HashMap();
			map.put("To", rows.getPhoneNumber());
			map.put("From", "+18589055732");
			map.put("Url",
					"http://demo.twilio.com/docs/voice.xml");
			map.put("Method", "GET");
			map.put("FallbackMethod", "GET");
			map.put("StatusCallbackMethod", "GET");
			map.put("Record", "false");
			map.put("To", rows.getPhoneNumber());
			*/
			
			callFactory = client.getAccount().getCallFactory();

			try {
				call = callFactory.create(params);
				rows.setResponse(call.getSid());
			} catch (TwilioRestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dataRows.add(rows);
		}
		return dataRows;
	}
	
}
