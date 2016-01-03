package com.example.faceoff;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SinglePhone extends Activity {
	ImageButton start_face_off_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_phone);
		
		//Changes font for p1 text
		Typeface tf = Typeface.createFromAsset(getAssets(),
               "fonts/CaviarDreams.ttf");
        TextView tv = (TextView) findViewById(R.id.p1);
        tv.setTypeface(tf);
        
        //Changes font for p2 text
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
               "fonts/CaviarDreams.ttf");
        TextView tv2 = (TextView) findViewById(R.id.p2);
        tv2.setTypeface(tf2);
		
		//Drop down menu of profiles. Uses profileArray for the names
		Spinner profiles_spinner = (Spinner) findViewById(R.id.profiles_spinner_p1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.profileArray);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		profiles_spinner.setAdapter(adapter);
		
		
		profiles_spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
				String selected_name = (String) parent.getItemAtPosition(pos);	//The name they chose is stored in selected_name
				
				for (int i=0; i<ProfileCreationLogic.Profiles.size(); i++)
				{
					if (selected_name == ProfileCreationLogic.Profiles.get(i).getProfileName())
					{
						MainActivity.activePlayers.add(ProfileCreationLogic.Profiles.get(i));
					}
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			} 
			
		});
		

		//Drop down menu of profiles. Uses profileArray for the names
				Spinner profiles_spinner2 = (Spinner) findViewById(R.id.profiles_spinner_p2);
				// Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.profileArray);
				// Specify the layout to use when the list of choices appears

				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
				profiles_spinner.setAdapter(adapter2);
				
				
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
				profiles_spinner2.setAdapter(adapter2);
		

				profiles_spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, 
				            int pos, long id) {
						String selected_name = (String) parent.getItemAtPosition(pos);	//The name they chose is stored in selected_name
						
						for (int i=0; i<ProfileCreationLogic.Profiles.size(); i++)
						{
							if (selected_name == ProfileCreationLogic.Profiles.get(i).getProfileName())
							{
								MainActivity.activePlayers.add(ProfileCreationLogic.Profiles.get(i));
							}
						}
					}
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		//Locate buttons in activity_single_phone.xml
		start_face_off_button = (ImageButton) findViewById(R.id.start_face_off);
		
		//Capture button clicks
		start_face_off_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				//Start player 1 offense class
				Intent intent1 = new Intent(SinglePhone.this, Player1Offense.class);
				startActivity(intent1);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_phone, menu);
		return true;
	}

}
