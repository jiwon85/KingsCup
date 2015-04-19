package com.iphoneteam.jiwon_85.cs4084;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
    Camera Activity: Contains all components of camera feature when
        King card is drawn.
    Authors: Conor Moroney, Ji Won Min
 */

public class CameraActivity extends ActionBarActivity {


    private static final int REQUEST_TAKE_PHOTO = 1;
    private File photoFile;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(getApplicationContext(), getString(R.string.error),
                        Toast.LENGTH_SHORT).show();
                finish();
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }
    }

    private void createShareToIntent(String type, String caption){


        Intent share = new Intent(Intent.ACTION_SEND);


        share.setType(type);

        try {
            overlay();
        } catch(IOException e){
            Toast.makeText(getApplicationContext(), getString(R.string.error),
                    Toast.LENGTH_SHORT).show();
            finish();
        }


        Uri uri = Uri.fromFile(photoFile);


        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.putExtra(Intent.EXTRA_TEXT, caption);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

    //    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
    private void overlay() throws IOException {


        Bitmap bmp2 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.filter);

        Bitmap bmp1 = BitmapFactory.decodeFile(photoFile.getAbsolutePath());

        //crop to square
        int width = bmp1.getWidth();
        int height = bmp1.getHeight();
        int crop = (width - height) / 2;
        bmp1 = Bitmap.createBitmap(bmp1, crop, 0, height, height);

        //scales the picture to the size of the filter
        bmp1 = Bitmap.createScaledBitmap(bmp1, bmp2.getWidth(), bmp2.getHeight(), false);

        try {
            ExifInterface exif = new ExifInterface(photoFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            //http://developer.android.com/reference/android/media/ExifInterface.html#ORIENTATION_ROTATE_180
            if (orientation == 6) { //orientation is facing left
                matrix.postRotate(90);
            }
            else if (orientation == 3) { //orientation is upside down
                matrix.postRotate(180);
            }
            else if (orientation == 8) { //orientation is facing right
                matrix.postRotate(270);
            }
            bmp1 = Bitmap.createBitmap(bmp1, 0, 0, bmp1.getWidth(), bmp1.getHeight(), matrix, true); // rotating bitmap
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error),
                    Toast.LENGTH_SHORT).show();
        }


        Bitmap bmOverlay = Bitmap.createBitmap(bmp2.getWidth(), bmp2.getHeight(), bmp2.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);



        FileOutputStream fOut = new FileOutputStream(photoFile);

        bmOverlay.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        fOut.flush();
        fOut.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
    }


    @Override
    public void onRestart(){
        super.onRestart();
        finish();
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_TAKE_PHOTO) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Thread background = new Thread() {
                    public void run() {

                        try {

                            String type = "image/*";
                            String captionText = getString(R.string.caption);
                            createShareToIntent(type, captionText);

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), getString(R.string.error),
                                    Toast.LENGTH_SHORT).show();
                        }
                        finally {
                            finish();
                        }
                    }
                };
                background.start();
                Intent i = new Intent(getApplicationContext(), Splash_activity.class);
                i.putExtra("LOADING", true);
                startActivity(i); //loading screen
            }
            else{
                finish();
            }

        }
    }
}
