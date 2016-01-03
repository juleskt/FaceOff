 package com.example.faceoff;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BaseFace extends Activity 
{
	Button camera_button;
	Button another_profile_button;
	Button main_menu_button;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	/** Create a file Uri for saving an image */
	private static Uri getOutputMediaFileUri(int type)
	{
	      return Uri.fromFile(getOutputMediaFile(type));
	}
	/** Create a File for saving an image */
	private static File getOutputMediaFile(int type)
	{
		// To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.
		
		System.out.println(Environment.getExternalStorageState());
		
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FaceOff");
		System.out.println(mediaStorageDir.getPath());
		// This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists())
	    {
	        if (! mediaStorageDir.mkdirs())
	        {
	        	System.out.println("FaceOff" + "failed to create directory");
	        	return null;
	        }
	    
	    }
	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    
	    if (type == MEDIA_TYPE_IMAGE)
	    {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } 
	    else 
	    {
	        return null;
	    }
	    return mediaFile;
	}
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_face);
		
		// create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
	  //start the image capture Intent
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	    
	  //Locate buttons in activity_base_face.xml
	  		camera_button = (Button) findViewById(R.id.camera_button);
	  		another_profile_button = (Button) findViewById(R.id.another_profile_button);
	  		main_menu_button = (Button) findViewById(R.id.main_menu_button);
	  		
	  //Capture button clicks
	  		camera_button.setOnClickListener(new OnClickListener() 
	  		{
	  			public void onClick(View arg0)
	  			{
	  				//Start BaseFace class which opens camera
	  				Intent intent1 = new Intent(BaseFace.this, BaseFace.class);
	  				startActivity(intent1);
	  			}
	  		});
	  		
	  		another_profile_button.setOnClickListener(new OnClickListener() 
	  		{
	  			public void onClick(View arg0)
	  			{
	  				//Start CreateProfile class so user can make another profile if his opponent needs to make one
	  				Intent intent2 = new Intent(BaseFace.this, CreateProfile.class);
	  				startActivity(intent2);
	  			}
	  		});
	  		
	  		main_menu_button.setOnClickListener(new OnClickListener() 
	  		{
	  			public void onClick(View arg0)
	  			{
	  				//Go back to main menu after they're done taking the base face
	  				Intent intent2 = new Intent(BaseFace.this, MainActivity.class);
	  				startActivity(intent2);
	  			}
	  		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
		{
			PictureInterpretation.Decode(fileUri,ProfileCreationLogic.Profiles.get(ProfileCreationLogic.Profiles.size()-1),"base");
			ProfileCreationLogic.Profiles.get(ProfileCreationLogic.Profiles.size()-1).setPath(fileUri.getPath());
			Toast toast = Toast.makeText(getApplicationContext(), "Successfully created profile!", Toast.LENGTH_SHORT);
			toast.show();
			Intent intent1 = new Intent(BaseFace.this, MainActivity.class);
			startActivity(intent1);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base_face, menu);
		return true;
	}
}