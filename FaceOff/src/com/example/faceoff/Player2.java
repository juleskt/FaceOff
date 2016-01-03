/*package com.example.faceoff;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Player2 extends Activity {
	Button start_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player2);
		
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
		
		
		//Locate buttons in activity_player2.xml
		start_button = (Button) findViewById(R.id.start_button);
		
		//Capture button clicks
		start_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			
				//Start StartFaceOff class
				Intent intent1 = new Intent(Player2.this, StartFaceOff.class);
				startActivity(intent1);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player2, menu);
		return true;
	}

}*/
