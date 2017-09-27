package com.example.myles.ui_senior_project;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Camera extends Activity {

    File image;
    ImageView mImageView;
    Button captureClothing;
    Spinner clothingTypeList;
    Toast message;
    ArrayAdapter<CharSequence> adapter;

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent(int actionCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch(actionCode) {
            case REQUEST_TAKE_PHOTO:
                image = null;

                try {
                    image = createImageFile();
                    mCurrentPhotoPath = image.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, image.getPath());
                    handleBigCameraPhoto();
                } catch (IOException e) {
                    e.printStackTrace();
                    message.makeText(getApplicationContext(),"Failed to create file...",Toast.LENGTH_LONG);
                    message.show();
                    image = null;
                    mCurrentPhotoPath = null;
                }
                break;

            default:
                break;
        } // switch

        startActivityForResult(takePictureIntent, actionCode);

        /*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                mCurrentPhotoPath = image.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, URI.fromFile(image));
            } catch (IOException ex) {
                // Error occurred while creating the File
                message.makeText(getApplicationContext(),"Failed to create file...",Toast.LENGTH_LONG);
                message.show();
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                File photoPath = getCacheDir();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

        }*/
    }

    private void handleBigCameraPhoto() {
        if (mCurrentPhotoPath != null) {
            setPic();
            mCurrentPhotoPath = null;
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();

        mImageView.setImageBitmap(bitmap);
        mImageView.setVisibility(View.VISIBLE);
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "JPEG_" + clothingTypeList.getSelectedItem() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        adapter = ArrayAdapter.createFromResource(this,R.array.clothingTypes,android.R.layout.simple_spinner_dropdown_item);

        mImageView = (ImageView) findViewById(R.id.capturePreview);
        clothingTypeList = (Spinner) findViewById(R.id.selectClothing);
        clothingTypeList.setAdapter(adapter);

        captureClothing = (Button)findViewById(R.id.captureClothing);

        captureClothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_TAKE_PHOTO);
            }
        });
    }
}
