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

public class Victory2 extends Activity
{
	ImageButton main_menu_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
  		setContentView(R.layout.activity_victory2);
  		
  		TextView tv = (TextView) findViewById(R.id.player2_wins);
        tv.setText(MainActivity.activePlayers.get(1).profileName + " Wins !");
  		
  		//Locate buttons in victory.xml
		main_menu_button = (ImageButton) findViewById(R.id.main_menu);
		
		//Capture button clicks
		main_menu_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				//Clears the activeplayers array to prep a new game
				for(int x = 0; x < MainActivity.activePlayers.size(); x++)
				{
					MainActivity.activePlayers.remove(x);
				}
				
				Intent intent1 = new Intent(Victory2.this, MainActivity.class);
				startActivity(intent1);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_phone, menu);
		return true;
	}}
