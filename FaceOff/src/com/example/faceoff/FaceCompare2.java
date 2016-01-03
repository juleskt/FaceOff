package com.example.faceoff;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FaceCompare2 extends Activity {

	ImageButton Quit_button;
	ImageButton another_round_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_face_compare2);
		
		//Changes font for results
		Typeface tf = Typeface.createFromAsset(getAssets(),
               "fonts/CaviarDreams.ttf");
        TextView tv = (TextView) findViewById(R.id.results);
        tv.setTypeface(tf);
        
        //Changes font for score
        TextView tv2 = (TextView) findViewById(R.id.score);
        tv2.setTypeface(tf);
		
		//Displays image taken by player 1 on offense
		String path1 = Environment.getExternalStorageDirectory()+ "/Pictures/FaceOff/Player2Offense.jpg";
		ImageView jpgview1 = (ImageView)findViewById(R.id.imageView1);
		BitmapFactory.Options options1 = new BitmapFactory.Options();
		options1.inSampleSize = 2;
		Bitmap bm1 = BitmapFactory.decodeFile(path1, options1);
		jpgview1.setImageBitmap(bm1);
		
		//Displays image taken by player 2 on defense
		String path2 = Environment.getExternalStorageDirectory()+ "/Pictures/FaceOff/Player1Defense.jpg";
		ImageView jpgview2 = (ImageView)findViewById(R.id.imageView2);
		BitmapFactory.Options options2 = new BitmapFactory.Options();
		options2.inSampleSize = 2;
		Bitmap bm2 = BitmapFactory.decodeFile(path2, options2);
		jpgview2.setImageBitmap(bm2);
		
		new processData().execute();

		//Locate buttons in activity_face_compare.xml
		Quit_button = (ImageButton) findViewById(R.id.Quit_button);
		another_round_button = (ImageButton) findViewById(R.id.another_round_button);
								
		//Capture button clicks
		Quit_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				//If player 1 wins go to victory screen
				//If player 2 wins go to victory2 screen
				
				if(MainActivity.activePlayers.get(0).points > MainActivity.activePlayers.get(1).points)
				{
					//Start MainActivity class
					Intent intent1 = new Intent(FaceCompare2.this, Victory.class);
					startActivity(intent1);
				}
				else
				{
					Intent intent1 = new Intent(FaceCompare2.this, Victory2.class);
					startActivity(intent1);
				}
			}
		});		
		another_round_button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//Start Player1Offense class
				Intent intent1 = new Intent(FaceCompare2.this, Player1Offense.class);
				startActivity(intent1);
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.face_compare2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private class processData extends AsyncTask <String, Integer, Double>
	{
		ArrayList<Double> faceOne;
		ArrayList<Double> faceTwo;
		
		ProgressDialog progress;
		
		protected void onPreExecute()
		{
			super.onPreExecute();
			System.out.println("Starting ASYNC");
			progress = ProgressDialog.show(FaceCompare2.this,"Powered by FaceMark API", "Analyzing...");
		}
		
		
		protected Double doInBackground(String... params)
		{
			faceOne = ComparisonLogic.vsBaseFace(MainActivity.activePlayers.get(1),MainActivity.activePlayers.get(0).newFace);
			faceTwo = ComparisonLogic.vsBaseFace(MainActivity.activePlayers.get(0),MainActivity.activePlayers.get(1).newFace);
			return ComparisonLogic.FaceVsFace(faceOne,faceTwo);
		}
		
		protected void onPostExecute(Double score)
		{
			Typeface tf = Typeface.createFromAsset(getAssets(),
		               "fonts/CaviarDreams.ttf");
			TextView tv2 = (TextView) findViewById(R.id.score);
	        tv2.setTypeface(tf);
	        //tv2.setText("Score: 1367");
	        tv2.setText(MainActivity.activePlayers.get(0).profileName + "'s Score: " + score.intValue());
			MainActivity.activePlayers.get(0).points += score;
			progress.dismiss();
		}
	}

}
