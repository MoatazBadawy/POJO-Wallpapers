package com.picsapp.moatazbadawy.pojo4k.Adapter.Picasso;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.ablanco.zoomy.Zoomy;
import com.picsapp.moatazbadawy.pojo4k.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import es.dmoral.toasty.Toasty;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static android.provider.MediaStore.Images;

/*
 * This class for display the image when click on it
 * It is get the data from the class have the images "Images in ArrayList"
 */
public class PicassoDisplayWallpaperImage extends AppCompatActivity {

    public static final int PERMISSION_WRITE = 0;
    ImageView imageView;
    Button back_icon, save, share, wallpaper, download_view;

    // load Bitmap to method save image
    private static Bitmap loadBitmap(String url) {
        try {
            InputStream in = new URL(url).openStream();
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wallpaper);

        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // MAKE THE APP SUPPORT ONLY ENGLISH
        ViewCompat.setLayoutDirection(getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_LTR);

        // back to last activity
        back_icon = findViewById(R.id.button_back_display_wallpaper);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Display the data in the ImageView with Picasso "ImageView that insert in the activity"
        imageView = findViewById(R.id.image_display);
        final Intent intent = getIntent();
        if (intent.hasExtra("imageUrl")) {
            String url = intent.getStringExtra("imageUrl");
            Picasso
                    .with(this)
                    .load(url)
                    .placeholder(R.drawable.loading_display_image) // load image
                    .into(imageView);
        }

        // Button to set the image as wallpaper
        wallpaper = findViewById(R.id.button_wallpaper);
        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    setHomeWallpaper();
                }
            }
        });

        // Button to share image
        share = findViewById(R.id.button_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    setShareImage();
                }
            }
        });

        // Button to download the image
        save = findViewById(R.id.button_download);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    setDownloadImage();
                }
            }
        });

        ZoomIn();
        MaterialShowcaseSequence();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    // MaterialShowcaseView "intro"
    public void MaterialShowcaseSequence () {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "intro_card");
        sequence.setConfig(config);
        download_view = findViewById(R.id.download_view);
        sequence.addSequenceItem(download_view,
                "From there you can save image in your phone", "Next");
        sequence.addSequenceItem(wallpaper,
                "From there you can set the image as Wallpaper to your phone", "Next");
        sequence.addSequenceItem(share,
                "From there you can share the image to your friends without downloading it", "Let's start");
        sequence.start();
    }

    // zoom in photo like instagram
    public void ZoomIn () {
        Zoomy.Builder builder = new Zoomy.Builder(this).target(imageView);
        builder.register();
    }

    // Download the image
    public void setDownloadImage() {
        Intent intent = getIntent();
        String URL = intent.getStringExtra("imageUrl");
        /* to get image name from url */
        URI uri = null;
        try {
            uri = new URI(URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        URL videoUrl = null;
        try {
            videoUrl = uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        File tempFile = new File(videoUrl.getFile());
        String fileName = tempFile.getName();
        saveMyImage("CandyPhotos", URL, fileName);
    }

    // Share the image
    public void setShareImage() {
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
        Bitmap bitmap = bitmapDrawable.getBitmap();
        String bitmapPath = Images.Media.insertImage(getContentResolver(), bitmap, "Hey, Check out this great wallpaper"
                + "You can download CandyPhotos app from Play Store to get great wallpaper images to your phone and pc", null);
        Uri bitmapUri = Uri.parse(bitmapPath);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, Check out this great wallpaper" +
                "You can download CandyPhotos app from Play Store to get great wallpaper images to your phone and pc");
        shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
        startActivity(Intent.createChooser(shareIntent, ""));
    }

    // Set Home wallpaper the image
    public void setHomeWallpaper() {
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
        Bitmap bitmap = bitmapDrawable.getBitmap();
        String bitmapPath = Images.Media.insertImage(getContentResolver(), bitmap, "", null);
        Uri bitmapUri = Uri.parse(bitmapPath);
        Intent intent = new Intent(Intent.ACTION_ATTACH_DATA).setDataAndType(bitmapUri, "image/jpeg")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                .putExtra("mimeType", "image/jpeg");
        startActivity(Intent.createChooser(intent, getString(R.string.background)));
    }

    // method to save image
    public void saveMyImage(String appName, String imageUrl, String imageName) {

        Bitmap bmImg = loadBitmap(imageUrl);
        File filename;
        try {
            String path1 = Environment.getExternalStorageDirectory()
                    .toString();
            File file = new File(path1 + "/" + appName);
            if (!file.exists())
                file.mkdirs();
            filename = new File(file.getAbsolutePath() + "/" + imageName
                    + ".jpg");
            FileOutputStream out = new FileOutputStream(filename);
            bmImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            ContentValues image = new ContentValues();
            image.put(Images.Media.TITLE, appName);
            image.put(Images.Media.DISPLAY_NAME, imageName);
            image.put(Images.Media.DESCRIPTION, "App Image");
            image.put(Images.Media.DATE_ADDED, System.currentTimeMillis());
            image.put(Images.Media.MIME_TYPE, "image/jpg");
            image.put(Images.Media.ORIENTATION, 0);
            File parent = filename.getParentFile();
            image.put(Images.ImageColumns.BUCKET_ID, parent.toString()
                    .toLowerCase().hashCode());
            image.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, parent.getName()
                    .toLowerCase());
            image.put(Images.Media.SIZE, filename.length());
            image.put(Images.Media.DATA, filename.getAbsolutePath());
            Uri result = getContentResolver().insert(
                    Images.Media.EXTERNAL_CONTENT_URI, image);
            Toasty.normal(getApplicationContext(), "image saved", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // runtime storage permission
    public boolean checkPermission() {
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if ((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_WRITE);
            return false;
        }
        return true;
    }

    // Request storage permissions result
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_WRITE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //do somethings
        }
    }
}
