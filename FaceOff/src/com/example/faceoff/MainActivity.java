package com.example.faceoff;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ImageButton tut_button;
	ImageButton single_phone_button;
	ImageButton Profile_button;
	//ArrayList (like list from STL in c++) holds names of all profiles
	public static ArrayList<String> profileArray = new ArrayList<String>();
	public static ArrayList<profile> activePlayers = new ArrayList<profile>();
	String num_profiles_left;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //Get the view from activity_main.xml
  		setContentView(R.layout.activity_main);
  		
  		ImageView image = (ImageView) findViewById(R.id.logo);
  		
  		//Changes font for face_off_name
		Typeface tf = Typeface.createFromAsset(getAssets(),
               "fonts/CaviarDreams.ttf");
        TextView tv = (TextView) findViewById(R.id.face_off_name);
        tv.setTypeface(tf);
        
		//Adds "Choose Profile" as first string in profileArray when app first runs
		if (profileArray.size() == 0)
		{
			profileArray.add("Choose Profile");
		}
		//Locate buttons in activity_main.xml
		tut_button = (ImageButton) findViewById(R.id.tut_button);
		single_phone_button = (ImageButton) findViewById(R.id.single_phone_button);
		Profile_button = (ImageButton) findViewById(R.id.Profile_button);
		
		//Dialog box tells user they need to make profiles before continuing to game
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	
		//Capture button clicks
		tut_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			
				//Start Tutorial class
				Intent intent1 = new Intent(MainActivity.this, Tutorial.class);
				startActivity(intent1);
			
			}
		});
	
		single_phone_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (profileArray.size() < 3)
				{	 
					if (profileArray.size() == 1)
					{
						num_profiles_left = "two";
					}
					else if (profileArray.size() == 2)
					{
						num_profiles_left = "one";
					}
					// set title
					alertDialogBuilder.setTitle("Missing Profiles");
		 
					
					// set dialog message
					alertDialogBuilder
						.setMessage("Looks like you haven't created enough profiles yet. You need " + num_profiles_left + " more to continue.")
						.setCancelable(false)
						.setNegativeButton("Return",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
				}
				else
				{
				
					//Start SinglePhone class
					Intent intent2 = new Intent(MainActivity.this, SinglePhone.class);
					startActivity(intent2);
			}
			}});
		
	
		Profile_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			
				//Start CreateProfile class
				Intent intent3 = new Intent(MainActivity.this, CreateProfile.class);
				startActivity(intent3);
			}
		});
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}