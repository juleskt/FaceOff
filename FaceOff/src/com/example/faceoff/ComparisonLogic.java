package com.example.faceoff;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class ComparisonLogic 
{
	/*
	 * (x,y) values for the base face, for a total of 68 points
	 * As of now, we only care about 15 - 20 (left eyebrow), 21-26 (right eyebrow),
	 * 27-30 (right eye excluding pupil), 32 - 35 (left eye excluding pupil), 48 - 66 (mouth).
	 * The total number of relevant points is 34 as of now. So each point will be weighted with the 
	 * value of 1/34
	 * 
	 * As of now, the only centering algorithms use the pupils as reference. If we want to streamline the code in the future,
	 * we have access to many points on the nose as well as the outline of the face (ear to ear and along the jaw).
	 * */
	public static ArrayList<Double>  vsBaseFace(profile p, ArrayList<Double> newFace)
	{
		//31 (30) - Right Pupil, 36 (35) Left Pupil, DON'T FORGET THAT INDICIES START AT 0, NOT 1
		
		ArrayList<Double> diffFromBase = new ArrayList<Double>();
		ArrayList<Double> shiftedBase = new ArrayList<Double>();
		
		//Gets the center of the baseface eye-line to re-center the baseFace to a theoretical (0,0)
		//double baseCenterX = ((p.baseFace.get(31) + p.baseFace.get(36))/2);
		//double baseCenterY = ((p.baseFace.get(99) + p.baseFace.get(104))/2);
		
		double baseCenterX = (p.baseFace.get(67));
		double baseCenterY = (p.baseFace.get(135));
		
		for(int x = 0; x < p.baseFace.size(); x++) //adjusting all baseface points to new origin
		{
			if(x < 68)
			{
				shiftedBase.add(p.baseFace.get(x)- baseCenterX);
			}
			else
			{
				shiftedBase.add(p.baseFace.get(x)- baseCenterY);
			}
		}
		
		//Gets the center of the new face eye-line to re-center the new face to a theoretical (0,0)
		//double faceCenterX = ((newFace.get(31) + newFace.get(36))/2);
		//double faceCenterY = ((newFace.get(99) + newFace.get(104))/2);
		
		double faceCenterX = (newFace.get(67));
		double faceCenterY = (newFace.get(135));
		
		for(int x = 0; x < newFace.size(); x++) //adjusting all newface points to new origin
		{
			if(x < 68)
			{
				newFace.set(x,newFace.get(x) - faceCenterX);
			}
			else
			{
				newFace.set(x,newFace.get(x) - faceCenterY);
			}
		}
		
		//Calculates the distance scaling 
		double scaleX = (newFace.get(31))/(shiftedBase.get(31));
		//double scaleY = (newFace.get(99))/(shiftedBase.get(99));
		
		for(int x = 0; x < newFace.size(); x++)
		{
			if( x < 68)
			{
				newFace.set(x,newFace.get(x)*scaleX);
			}
			else
			{
				newFace.set(x,newFace.get(x)*scaleX);
			}
		}
		
		/*Loops through the ArrayList of points, formatted as all x values then y values (0-67 x, 68 - 135 y) and compares the differences
		 * between the two points in question. The baseface coordinates are centered to the newface by calculating the differences in 
		 * the location of pupils as done above. The loop appends the differences into an ArrayList called diffFromBase and returns it.
		 * */
		
		for(int x = 0; x < p.baseFace.size(); x++)
		{
			diffFromBase.add(newFace.get(x) - shiftedBase.get(x));
		}
		System.out.println("Scale(x): " + scaleX);
		//System.out.println("Scale(y): " + scaleY);
		//System.out.println(shiftedBase);
		//System.out.println(newFace);
		//System.out.println(diffFromBase);
		
		return diffFromBase;
	}

	public static Double FaceVsFace(ArrayList<Double> faceOne, ArrayList<Double> faceTwo)
	{
		double points = 0;
			
		/*Calculates the centering distance needed to transpose the pupils of face two onto face one to accurate facial feature change 
		 * detection. */
			
		/*
		 * This loop adds the differences of differences for both faces and appends them to a list
		 * called comparedPoints. It takes the opposite of one of the points and adds them together.
		 * Therefore, the closer to 0 the value is, the better the faces at that point match. Need
		 * to add an algorithm to take care of only the points we need and add them to points.
		 * (x,y) values for the base face, for a total of 68 points
		 * As of now, we only care about 15 - 20 (left eyebrow), 21-26 (right eyebrow),
		 * 27-30 (right eye excluding pupil), 32 - 35 (left eye excluding pupil), 48 - 66 (mouth).
		 * The total number of relevant points is 34 as of now. So each point will be weighted with the 
		 * value of 1/34*/
		
		
		/*The following are the X values of landmark points being compared. If the are in the general direction of the moved point, the user will
		 * get points for it :)*/
		
		for(int x = 0; x <= faceOne.size()/2; x++)
		{
			if(faceOne.get(x) > 0 && faceTwo.get(x) > 0)
			{
				++points;
			}
			else if(faceOne.get(x) < 0 && faceTwo.get(x) < 0)
			{
				++points;
			}
		}
		return (points/135)*100;
	}
}
