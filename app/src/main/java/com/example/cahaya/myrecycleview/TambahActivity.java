package com.example.cahaya.myrecycleview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cahaya.myrecyclerview.R;

import java.io.IOException;

public class TambahActivity extends AppCompatActivity {

    EditText Id,Nama,Spesifikasi,Tahun,Harga,Jumlah,Satuan,Kondisi;
    Button Tambah;
    ImageView img_item_photo;

    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;
    private Uri filePath;
    private String realpath;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tambah);

        requestStoragePermission();

        Id =(EditText) findViewById(R.id.id_edit);
        Nama=(EditText) findViewById(R.id.nama_edit);
        Spesifikasi = (EditText) findViewById(R.id.spesifikasi_edit) ;
        Tahun = (EditText) findViewById(R.id.tahun_edit);
        Harga = (EditText) findViewById(R.id.harga_edit);
        Jumlah = (EditText) findViewById(R.id.jumlah_edit);
        Satuan = (EditText) findViewById(R.id.satuan_edit);
        Kondisi = (EditText) findViewById(R.id.kondisi_edit);
        Tambah = (Button) findViewById(R.id.tambah);
        img_item_photo =(ImageView) findViewById(R.id.img_item_photo);

        Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        if(savedInstanceState!=null){
            realpath = savedInstanceState.getString("path");
            img_item_photo.setImageBitmap(BitmapFactory.decodeFile(realpath));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tambah_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.tambah:
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
                    uploadMultipart();
                }else {
                    Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return true;
    }

        public void uploadMultipart() {
        String id = Id.getText().toString().trim();
        String nama = Nama.getText().toString().trim();
        String spesifikasi = Spesifikasi.getText().toString().trim();
        String tahun = Tahun.getText().toString().trim();
        String harga = Harga.getText().toString().trim();
        String jumlah = Jumlah.getText().toString().trim();
        String satuan = Satuan.getText().toString().trim();
        String kondisi = Kondisi.getText().toString().trim();

        if(id==null){
        Toast.makeText(this,"Tidak Ada",Toast.LENGTH_SHORT).show();
        }

//        try {
//            //getting the actual path of the image
//            String path = realpath;
//
//            //Uploading code
//            String uploadId = UUID.randomUUID().toString();
//
//            //Creating a multi part request
//             new MultipartUploadRequest(this, uploadId, Api.URL_CREATE_INVENT)
//            .addFileToUpload(path, "image") //Adding file
//            .addParameter("id", id)
//            .addParameter("nama", nama)
//            .addParameter("spesifikasi", spesifikasi)
//            .addParameter("tahun", tahun)
//            .addParameter("harga", harga)
//            .addParameter("jumlah",jumlah)
//            .addParameter("satuan",satuan)
//            .addParameter("kondisi", kondisi)
//                     .setMaxRetries(2)
//            .startUpload(); //Starting the upload
//            Toast.makeText(this, " berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//
//        } catch (Exception exc) {
//            if(exc.getMessage()=="uri"){
//                Toast.makeText(this, "Gambar belum dipilih", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
}

private void showFileChooser() {
            Intent intent;
            intent = new Intent();
            intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) { filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img_item_photo.setImageBitmap(bitmap);
                realpath = getPath(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }

    public String getPath(Uri uri) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = getContentResolver().query(
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return path;
        }

private void requestStoragePermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                return;
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //If the user has denied the permission previously your code will come to this block
                // Here you can explain why you need this permission
                // Explain here why you need this permission
            }
            //And finally ask for the permission
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

@Override
protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString("path",realpath);
        }

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            //Checking the request code of our request
    if (requestCode == STORAGE_PERMISSION_CODE) {
        //If permission is granted
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Displaying a toast
            Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
        } else {
            //Displaying another toast if permission is not granted
            Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
        }
    }
        }
}


