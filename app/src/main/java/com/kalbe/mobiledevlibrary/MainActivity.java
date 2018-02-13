package com.kalbe.mobiledevlibrary;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsEnumDatePicker.*;
import com.kalbe.mobiledevknlibs.Converter.Converter;
import com.kalbe.mobiledevknlibs.DrawableListener.DrawableClickListener;
import com.kalbe.mobiledevknlibs.InputFilter.InputFilter;
import com.kalbe.mobiledevknlibs.InputFilter.InputFilters;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.Intent.TypeDataIntent;
import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevknlibs.PDFView.PDFViewer;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickFile;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImage;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImageCustom;
import com.kalbe.mobiledevknlibs.PickImageAndFile.UriData;
import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevknlibs.Table.Table;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbe.mobiledevlibrary.common.clsItem;
import com.kalbe.mobiledevlibrary.repo.clsItemRepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ImageView img;
    TextView tvFile;
    String txtPathUserData;
    Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        txtPathUserData= Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator;
//        ToastCustom.showToasty(getApplicationContext(), "tes");
        clsItem item = new clsItem();
        item.setItemId(1);
        item.setTxtItemCode("MLN310-92");
        item.setTxtItemName("Child go");
        item.setTxtCategory("Makanan");
        item.setTxtPrice("905.000");
        item.setTxtStock("100");
        item.setBitActive(1);
        item.setTxtVarian("");
        try {
            new clsItemRepo(getApplicationContext()).create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        int a = 5/0;
        Spinner spn = (Spinner) findViewById(R.id.tesSpinner);
        img = (ImageView) findViewById(R.id.tesImg);
        Button btn = (Button) findViewById(R.id.btntes);
        Button btnFile = (Button)findViewById(R.id.btnFile);
        tvFile = (TextView) findViewById(R.id.tvFile);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastCustom.showToasty(getApplicationContext(), "tes", 5);
//                PickFile.intentPickFile(MainActivity.this, 500);
            }
        });
        final EditText date = (EditText) findViewById(R.id.txtDate);

         //ini contoh penggunaan input filter
        EditText etInputFilter = (EditText) findViewById(R.id.txt_input_filter);
        char[] chars = {'.', ',', '-'};
        char[] chars1 = {};
//        InputFilters.etTextWatcherSPChar(etInputFilter, null, chars);
        InputFilters.etTextWatcherNoSpaceAtFirst(etInputFilter, null, chars1);

//        InputFilter.filterCapsAll(etInputFilter, 20);
//        InputFilter.filterSpaceAtoZ0to9(etInputFilter);

//        ToastCustom.showToasty(getApplicationContext(), "tes", 5);
//        clsDatePicker.showDatePicker(MainActivity.this, date);



/*
//        CustomDatePicker.showHint(date, clsEnumDatePicker.standard2);
        clsDatePicker.showHint(date, clsEnumDatePicker.standard0);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CustomDatePicker.showDatePicker(MainActivity.this, date, "Set Date", 0);
//                clsDatePicker.showDatePicker(MainActivity.this, date, clsEnumDatePicker.standard0);
                ToastCustom.showToasty(getApplicationContext(), "tes", 2);
            }
        });
*/


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value[]= {};
                IntentCustom.intentToActivity(MainActivity.this, Main2Activity.class, "tes", value);

               /* //ini untuk pick file biasa aja
                String[] mimetypes = {"application/pdf" , "application/msword" , "application/vnd.ms-excel"};
                PickFile.intentPickFile(MainActivity.this, 310, mimetypes);
                */

//                File file = new File(txtPathUserData, "tes" );
//                byte[] tes = IntentCustom.getByteImageToSave(getApplicationContext(), uri);
//                new PopUpMaps().popUpMaps(MainActivity.this, R.layout.popup_map);
//                byte[] tesFile = null;
//                try {
//                     tesFile = PickFile.getByteArrayFileToSave(uri, getApplicationContext());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                ToastCustom.showToastDefault(getApplicationContext(), String.valueOf(tesFile));


//                String file = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator + "UUD.pdf" ;
//                IntentCustom.intentPDViewer(MainActivity.this, UriData.getOutputMediaUrifromFile(MainActivity.this, new File(file)), false);//intent untuk view pdf menggunakan pdf viewer

/*
                //ini untuk intent action view yang untuk melihat filenya membutuhkan aplikasi 3rd party
                Uri uri = UriData.getOutputMediaUrifromFile(MainActivity.this, new File(file));
                IntentCustom.intentActionView(MainActivity.this, uri, TypeDataIntent.DOC);
*/
//                Intent tes =  new Intent(MainActivity.this, PDFViewer.class);
//                tes.putExtra("pdf", new File(file));
//                startActivity(tes);
//                TesFragment fragmentDownloadData = new TesFragment();
//                Fragment fragment = Converter.FragmentStringToClas(MainActivity.this, "TesFragment");
//                FragmentTransaction fragmentTransactionDownloadData = getSupportFragmentManager().beginTransaction();
//                fragmentTransactionDownloadData.replace(R.id.frame, fragment);
//                fragmentTransactionDownloadData.commit();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take image ke kamera seperti biasanya
//                PickImage.CaptureImage(MainActivity.this, txtPathUserData, "tes", 400);
                //take image dari kamera atau dari galery (custom) untuk dp
                PickImageCustom.selectImageProfile(MainActivity.this, txtPathUserData, 600, 700);
            }
        });



        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");
        SpinnerCustom.setAdapterSpinner(spn, getApplicationContext(), R.layout.custom_spinner, categories);
//        spn.setAdapter(new SpinnerCustom.MySpinnerAdapter(getApplicationContext(), R.layout.custom_spinner, categories));
        SpinnerCustom.selectedItemByText(getApplicationContext(), spn, categories, "Travel");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 400) {
            if (resultCode == -1) {
                try {
                    uri = UriData.getOutputMediaImageUri(getApplicationContext(),txtPathUserData, "tes" );
                    Bitmap bitmap = PickImage.decodeStreamReturnBitmap(getApplicationContext(), uri);
                    PickImage.previewCapturedImage(img, bitmap, 150, 150);

                    /*
                    //kalo mau menggunakan decodefilr return bitmap maka harus di definisikan dulu urinya\
                    //cara mendefinisikannya seperti ini :
                    Uri uriimage = UriData.getOutputMediaImageUriCons(MainActivity.this, txtPathUserData);
                    //uriData yang ada cons di belakanya tandanya nama file/imagenya sudah di deklarasikan tinggal memberikan
                    //path foldernya

                    Bitmap bmp= PickImage.decodeFileReturnBitmap(MainActivity.this, uriimage);
                    PickImage.previewCapturedImage(img, bmp, 150, 150);
                    */

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 500){
            if (resultCode == -1){
                try {
                    String tes = PickFile.getFileName(getApplicationContext(), resultCode, data);
                    uri = UriData.getOutputMediaFileUri(txtPathUserData, tes );
                    PickFile.moveFileToSpecificUri(getApplicationContext(),data, uri);
                    tvFile.setText(tes);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode == 310){
            if (resultCode == -1){
                IntentCustom.intentPDViewer(MainActivity.this, data.getData(), false);
            }
        }else if (requestCode == 600){
            if (resultCode == -1){
                //definisikan dulu Uri nya
                Uri image = UriData.getOutputMediaImageUriCons(MainActivity.this, txtPathUserData);
                PickImageCustom.performCropProfile(image, MainActivity.this, 800);

            }
        }else if (requestCode == 700){
            if (resultCode == -1){

               //untuk image yang di ambil dari gallery uri nya dari intentnya : data.getData()

                /*Bitmap bitmap = PickImage.decodeStreamReturnBitmap(getApplicationContext(), data.getData());
                //decodeStream return bitmap bisa di gunakan baik dari uri yang di definisikan terlebih dahulu maupun
                //uri yang di dapat dari intentReturnData : data.getData()
                PickImage.previewCapturedImage(img, bitmap, 150, 150);
                */
                PickImageCustom.performCropCustom(data.getData(), MainActivity.this, 800);
            }
        }else if (requestCode == 800){
            if (resultCode == -1){
                //ini untuk get bitmap dari hasil cropping, dengan memasukkan request code yang sama baik gambar yang di ambil dari kamera maupun dari gallery
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data"); //key "data" setting defaultnya
                PickImage.previewCapturedImage(img, thePic, 150, 150);
            }
        }
    }

    private void viewList(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(R.layout.popup_tables, null);
        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);

        String[] colTextHeader = {"Nama", "Qty", "ED"};
        ArrayList<String[]> data = new ArrayList<>();
        String tes = "gula";
        String tes2 = "1 kg";
        String tes3 = "Rp. 25000";
        String[] datas = {tes, tes2, tes3};
        data.add(datas);
        //cara menggunakan table library ini dengan memasukkan parameter context, nama tableLayoutnya, string array nama headernya, list string array datanya
        Table.setTableLayout(context, tlb, colTextHeader, data);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
}
