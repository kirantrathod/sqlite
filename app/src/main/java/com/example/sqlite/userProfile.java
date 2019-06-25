package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import id.zelory.compressor.Compressor;

public class userProfile extends AppCompatActivity {
    private TextView name;
    private Button AddImage;
    private ImageView img;
    private DBHelper db2;
    private static final int GALLARY_PICK=1;
    int i=0;
    private String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        db2=new DBHelper(this);
        name=(TextView)findViewById(R.id.NameOF);
        img=(ImageView)findViewById(R.id.image);
        AddImage=(Button)findViewById(R.id.addImage);
        Intent getintent=getIntent();
        String Name=getintent.getStringExtra("NAME");
       Id = getintent.getStringExtra("ID");
        name.setText(Id);
        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallaryintent=new Intent();
                gallaryintent.setType("image/*");
                gallaryintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallaryintent,"select image"),GALLARY_PICK);
               /* Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Log.i("photo", "" + intent);
                startActivityForResult(intent, i);
                i = i + 1;*/

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       /* if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            db2.insertImage(Id,b);
            Cursor c33=db2.FetchImage(Id);
            byte[] image1=c33.getBlob(1);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image1, 0, image1.length);
            img.setImageBitmap(bitmap);
        }*/
        if (requestCode == GALLARY_PICK && resultCode == RESULT_OK) {
            Uri imageuri = data.getData();
            CropImage.activity(imageuri)
                    .setAspectRatio(3, 3)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
