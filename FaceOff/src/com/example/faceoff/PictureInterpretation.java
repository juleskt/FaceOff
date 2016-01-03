package com.example.faceoff;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.*;
import com.mashape.unirest.*;

import org.json.*;

public class PictureInterpretation 
{
	static ProgressDialog progress;
	public static ArrayList<Double> Decode(final Uri picture,final profile profile,final String code)
	{    
		final ArrayList<Double> interpretedVals = new ArrayList<Double>();
		
		class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> 
		{	
	    	protected HttpResponse<JsonNode> doInBackground(String... msg) 
	    	{	
	    		HttpResponse<JsonNode> request = null;
	    		
	    		if(picture != null)
	    		{
	    			try 
					{
						request = Unirest.post("https://apicloud-facemark.p.mashape.com/process-file.json")
								.header("X-Mashape-Key", "O5pPl3KTaVmshRGGD5FykeKF31gXp15vSBMjsnfMHFofluIQtP")
								.field("image", new File(picture.getPath()))
								.asJson();
					} 
					catch (UnirestException e) 
					{
						e.printStackTrace();
					}	
	    		}
	    		else
	    		{
	    			System.out.println("Picture is not real");
	    		}
	    		return request;
	    	}
	    	
	    	protected void onPostExecute(HttpResponse<JsonNode> response) 
	    	{
	    		JSONArray Array = response.getBody().getArray();
	        	System.out.println(response.getHeaders());
	        	
				try
				{
					int length = Array.getJSONObject(0).getJSONArray("faces").getJSONObject(0).getJSONArray("landmarks").length();
					
					System.out.println("JSON Length: " + length);
					
					for(int x = 0; x < length; x++)
					{
						interpretedVals.add(Array.getJSONObject(0).getJSONArray("faces").getJSONObject(0).getJSONArray("landmarks").getJSONObject(x).getDouble("x"));
					}
					for(int x = 0; x < length; x++)
					{
						interpretedVals.add(Array.getJSONObject(0).getJSONArray("faces").getJSONObject(0).getJSONArray("landmarks").getJSONObject(x).getDouble("y"));
					}
					
					if(code == "base")
					{
						profile.addBaseFace(interpretedVals);
					}
					else if(code == "face")
					{
						profile.setNewFace(interpretedVals);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				System.out.println("InterpretedVals Length: " + interpretedVals.size());
	    	}
		}
		
		new CallMashapeAsync().execute();
	
		return interpretedVals;
	}
}
