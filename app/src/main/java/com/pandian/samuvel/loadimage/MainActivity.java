package com.pandian.samuvel.loadimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button loadImageButton;
    private ImageView resultImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadImageButton = (Button) findViewById(R.id.loadImageButton);
        resultImageView = (ImageView) findViewById(R.id.mainResultImageView);

        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                Uri imageUri = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
               // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
               // selectedImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
               // byte[] imageBytes = byteArrayOutputStream.toByteArray();
                Intent intent = new Intent(this,ResultActivity.class);
                intent.putExtra("imageBytes",imageUri);
                startActivity(intent);
               // resultImageView.setImageBitmap(selectedImage);

            }

        }
    }
}
