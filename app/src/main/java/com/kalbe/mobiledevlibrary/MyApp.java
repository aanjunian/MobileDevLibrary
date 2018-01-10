package com.kalbe.mobiledevlibrary;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.kalbe.mobiledevknlibs.ErrorReporting.LocalReportSenderAcra;
import com.kalbe.mobiledevknlibs.ErrorReporting.ModelError;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.io.File;
import java.util.List;

/**
 * Created by Dewi Oktaviani on 1/9/2018.
 */
@ReportsCrashes(
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text, // optional, displayed as soon as the crash occurs, before collecting data which can take a few seconds
        resDialogText = R.string.crash_dialog_text,
        resDialogIcon = android.R.drawable.ic_dialog_info, //optional. default is a warning sign
        resDialogTitle = R.string.crash_dialog_title, // optional. default is your application name
        resDialogCommentPrompt = R.string.crash_dialog_comment_prompt, // optional. When defined, adds a user text field input with this text resource as a label
        resDialogOkToast = R.string.crash_dialog_ok_toast, // optional. displays a Toast message when the user accepts to send a report.

        customReportContent = {
                ReportField.APP_VERSION_CODE,
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PHONE_MODEL,
                ReportField.BRAND,
                ReportField.CUSTOM_DATA,
                ReportField.INITIAL_CONFIGURATION,
                ReportField.CRASH_CONFIGURATION,
                ReportField.USER_CRASH_DATE,
                ReportField.STACK_TRACE,
                ReportField.LOGCAT,
                ReportField.USER_COMMENT,
                ReportField.DEVICE_ID,
                ReportField.FILE_PATH}
)
public class MyApp extends Application{
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ACRA.init(this);
        String txtPathUserData= Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator;
        ACRA.getErrorReporter().setReportSender(new LocalReportSenderAcra(this, txtPathUserData));
        List<ModelError> modelErrors = LocalReportSenderAcra.getModelErrorList(context);
    }
}
