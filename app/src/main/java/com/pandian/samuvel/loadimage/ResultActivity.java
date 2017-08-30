package com.pandian.samuvel.loadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResultActivity extends AppCompatActivity {
    private ImageView resultImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultImage = (ImageView) findViewById(R.id.resultImage);

        Bundle bundle = getIntent().getExtras();
        Uri uri = (Uri) bundle.get("imageBytes");
        InputStream imageStream = null;
        try {
            imageStream = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        resultImage.setImageBitmap(selectedImage);
        //byte[] bytes = bundle.getByteArray("imageBytes");
        //Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        //resultImage.setImageBitmap(bitmap);
    }
}
