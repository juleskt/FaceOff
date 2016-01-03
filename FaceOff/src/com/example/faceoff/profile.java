package com.example.faceoff;

import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class profile 
{		
		//Name of the profile
		String profileName;
		
		//Amount of points this profile is carrying
		double points;
		
		/* ArrayList of specified points on the face, 68 (x,y) coordinates packed into a 135 element array with the format of:
		 * X coordinates first (0-67) followed by the y coordinates (68-135)
		 * */ 
		ArrayList<Double> baseFace;
		
		ArrayList<Double> newFace;
		
		//The element of the current profile. assuming many will be stored
		int profileNum;
		
		//The string path to the baseFace
		String path;
		
		//The string path to the newly taken picture
		String newPath;
		
		//Constructor for a profile
		public profile(String name, ArrayList<Double> arrayMap, int index)
		{
			profileName = name;
			points = 0;
			profileNum = index;
			baseFace = arrayMap;
		}
		
		public profile(String name, int index)
		{
			profileName = name;
			points = 0;
			profileNum = index;
		}
		
		public void addBaseFace(ArrayList<Double> arrayMap)
		{
			baseFace = arrayMap;
		}
		
		public double getPoints()
		{
			return this.points;
		}
		
		public String getProfileName()
		{
			return this.profileName;
		}
		
		public int getIndex()
		{
			return this.profileNum;
		}
		
		public void setProfileName(String newName)
		{
			this.profileName = newName;
		}
		
		public void setPath(String path)
		{
			this.path = path;
		}
		public void setNewFace(ArrayList<Double> arrayMap)
		{
			this.newFace = arrayMap;
		}
		public void setNewPath(String path)
		{
			this.newPath = path;
		}
}
