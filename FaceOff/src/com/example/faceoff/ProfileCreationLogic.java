package com.example.faceoff;

import java.io.File;
import java.util.*;
import org.json.*; //May or may not need that

import android.graphics.Bitmap;
import android.net.Uri;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
/*
 * This class contains the logic to creating a profile object complete with a name, accrued points, 
 * and a baseface. Once the camera is implemented, we can pass in the image and name into a function call
 * to get the baseface values of the photo via API. After mapping the photo,
 * a new object is instantiated and will be put into an object storage container. (Probably an object array
 * at this point)
 * */

public class ProfileCreationLogic 
{
	//The list of objects (should find out how to store in memory)
	static ArrayList<profile> Profiles = new ArrayList<profile>();
	
	//Method for creating the Profile Object with a name and BaseFace
	public static void CreateProfile(String name)
	{		
		//Adds a new profile object into the list Profiles
		Profiles.add(new profile(name,Profiles.size()));
	}
}


