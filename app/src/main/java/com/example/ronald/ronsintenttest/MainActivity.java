package com.example.ronald.ronsintenttest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.webkit.WebView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView myWebView;

    public static Bitmap getBitmapFromView(View view)  {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        //throw new NullPointerException();
        return bitmap;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnwebView = (Button) findViewById(R.id.btnWebView);
        btnwebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MyWebView.class);
                startActivity(i);
            }
        });

    }

    public void process(View view) {
        Intent intent, chooser;
        if(view.getId() == R.id.LaunchMap) {

            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:19.076,72.877"));
            startActivity(intent);
        }
        else if(view.getId() == R.id.LaunchMarket) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.snapchat.android"));
            chooser = Intent.createChooser(intent, "Launch Market");
            startActivity(chooser);
        }
       else if(view.getId() == R.id.LaunchEmail) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String [] to = {"rformusoh1@gmail.com","rformusoh@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "sent from app");
            intent.putExtra(Intent.EXTRA_TEXT,"whats good?");
            intent.setType("message/rfc822");
            chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);

        }
        else if (view.getId() == R.id.SendImage) {

            ImageView iv = new ImageView(getApplicationContext());

            iv.setImageResource(R.drawable.small);

            BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            File sdCardDirectory = Environment.getExternalStorageDirectory();

            File image = new File(sdCardDirectory, "test.png");

            boolean success = false;

            // Encode the file as a PNG image.
            FileOutputStream outStream;
            try {

                outStream = new FileOutputStream(image);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        /* 100 to keep full quality of the image */

                outStream.flush();
                outStream.close();
                success = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (success) {
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,"title", null);
                Uri screenshotUri = Uri.parse(path);
                final Intent emailIntent1 = new Intent(     android.content.Intent.ACTION_SEND);
                emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailIntent1.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                emailIntent1.setType("image/png");
                startActivity(Intent.createChooser(emailIntent1, "Send email using"));
            } else {
                Toast.makeText(getApplicationContext(),
                        "Error during image saving", Toast.LENGTH_LONG).show();
            }


        }
    }
}
