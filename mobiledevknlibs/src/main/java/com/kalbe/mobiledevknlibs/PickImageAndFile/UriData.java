package com.kalbe.mobiledevknlibs.PickImageAndFile;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dewi Oktaviani on 1/8/2018.
 */

public class UriData {
    private static final String IMAGE_DIRECTORY_NAME = "Image";
    //get lication file to save using file path
    public static Uri getOutputMediaUrifromFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName()+".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    //get location file to save tmp using string path
    public static Uri getOutputMediaFileUri(Context context, String folderName, String fileName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName()+ ".provider", getOutputMediaImage(folderName, fileName));
        } else {
            return Uri.fromFile(getOutputMediaImage(folderName, fileName));
        }
    }

    //create path file
    public static Uri getOutputMediaFileUri(String folderName, String fileName) {
        // External sdcard location

        File mediaStorageDir = new File(folderName + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        File mediaFile = null;
        if (fileName.length()>0){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName );
        }

        return Uri.fromFile(mediaFile);
    }

    //create path file
    public static File getOutputMediaImage(String folderName, String fileName) {
        // External sdcard location

        File mediaStorageDir = new File(folderName + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName + ".png");
        return mediaFile;
    }
}
