package com.example.faceoff;
 
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
 
/* Need to implement a dropdown menu instead of a single button.
 * As of now, the only button on this page is tutorial, I don't think it actually
 * needs to be here, as we already have a tutorial button that will show users
 * how to do things. After hitting the CreateProfile button, the user should be prompted with a
 * text field that asks for the name they want to name the profile. Then, the camera portion will start
 * to take a picture. The picture is in Bitmap format by default I believe.
 * In the future we should have a "Is this ok? Y/N option but for now it is fine.
 * */
 
public class CreateProfile extends Activity
{
        ImageButton baseface_button;
        ImageButton base_face_help_button;
        EditText name_entry;
 
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_create_profile);
               
                //font for create profile text
                Typeface tf = Typeface.createFromAsset(getAssets(),
               "fonts/CaviarDreams.ttf");
        TextView tv = (TextView)findViewById(R.id.create_profile);
        tv.setTypeface(tf);
               
                name_entry = (EditText)findViewById(R.id.name_entry);
               
                //Locate buttons in activity_create_profile.xml
                baseface_button = (ImageButton)findViewById(R.id.baseface_button);
                base_face_help_button = (ImageButton)findViewById(R.id.base_face_help_button);
               
                //Capture button clicks
                baseface_button.setOnClickListener(new OnClickListener()
                {
                        public void onClick(View arg0)
                        {
                                String name_text = name_entry.getText().toString();     //Name stored in name_text
                                MainActivity.profileArray.add(name_text);       //Name added to profileArray ArrayList
                                ProfileCreationLogic.CreateProfile(name_text); //Creates a profile with the name entered
                                Intent intent = new Intent(CreateProfile.this,BaseFace.class);
                                startActivity(intent);
                        }
                });
               
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                base_face_help_button.setOnClickListener(new OnClickListener()
                {
                        public void onClick(View arg0)
                        {        
                                // set title
                                alertDialogBuilder.setTitle("What's a Base Face?");
         
                                // set dialog message
                                alertDialogBuilder
                                        .setMessage("A Base Face is our way of comparing your photos in the face off to what your normal face looks like. " +
                                                        "So take a simple picture with your face centered in the camera, and we'll use it to reward you points later!")
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
                        });    
        }
 
        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.create_profile, menu);
                return true;
        }
}
