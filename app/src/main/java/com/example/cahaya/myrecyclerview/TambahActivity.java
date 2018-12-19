package com.example.cahaya.myrecyclerview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import java.util.function.ToLongBiFunction;

public class TambahActivity extends AppCompatActivity {





    EditText Id,Nama,Spesifikasi,Tahun,Harga,Jumlah,Satuan,Kondisi;
    Button tambah;
    ImageView img_item_photo;

    private int id_barang=1;
    Private static final int STORAGE_PERMISSION_CODE = 123;
    private Bitmap bitmap;
    private Url filepath;
    private String realpath;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tambah);

        requestStoragePermission();
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle("Tambah");
    setSupportActionBar(toolbar);

    Id =(EditText) findViewById(R.id.id_edit);
    Nama=(EditText) findViewById(R.id.nama_edit);
   Spesifikasi = (EditText) findViewById(spesifikasi_edit) ;
   Tahun = (EditText) findViewById(tahun_edit);
   Harga= (EditText) findViewById(harga_edit);
    Jumlah= (EditText) findViewById(jumalah_edit);
    Satuan= (EditText) findViewById(satuan_edit);
    Kondisi= (EditText) findViewById(kondisi_edit);
    tambah=(Button) findViewById(R.id.tambah);
    id_image_photo=(ImageView) findViewById(R.id.id_image_photo);

    tambah.setOnContextClickListener(new View.onClickListener);{
      @Override
              public void onClick (View v) {showFileChooser();
    }
    if

}





        }
