package com.nikhil.contentprovider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_GET_FILE = 100;
    private static final int REQUEST_GET_IMAGE = 200;

    private Button bt_file, bt_image;
    private TextView tv_file;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_file = findViewById(R.id.bt_file_provider);
        bt_image = findViewById(R.id.bt_image_provider);
        tv_file = findViewById(R.id.tv_file);
        iv_image = findViewById(R.id.iv_image);

        bt_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                String[] mimeTypes = {"application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, REQUEST_GET_FILE);
            }
        });

        bt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra("type", "camera");
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.putExtra("type", "gallery");

                Intent chooserIntent = Intent.createChooser(captureIntent, "Pick image from");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {galleryIntent});
                startActivityForResult(chooserIntent, REQUEST_GET_IMAGE);

//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if(intent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
//                }
//
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_GET_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_GET_FILE ) {
            if(resultCode == RESULT_OK) {
                if(data != null) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        File file = new File(uri.getPath());
                        tv_file.setText(file.getName());
                    }
                }
            }
        }

        if(requestCode == REQUEST_GET_IMAGE) {
            if(resultCode == RESULT_OK) {
                if (data != null) {
                    if(data.getExtras() != null) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        iv_image.setImageBitmap(bitmap);
                    } else {
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        iv_image.setImageBitmap(bitmap);
                    }
                }
            }
        }

//        if(requestCode == REQUEST_GET_IMAGE) {
//            if(resultCode == RESULT_OK) {
//                if(data != null) {
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    iv_image.setImageBitmap(bitmap);
//                }
//            }
//        }
//
//        if(requestCode == REQUEST_CAPTURE_IMAGE) {
//            if(resultCode == RESULT_OK) {
//                if(data != null) {
//                    if(data.getExtras() != null) {
//                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                        iv_image.setImageBitmap(bitmap);
//                    }
//                }
//            }
//        }
     }
}
