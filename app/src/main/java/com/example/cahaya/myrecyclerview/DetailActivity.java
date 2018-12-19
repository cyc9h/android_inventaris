package com.example.cahaya.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {



    TextView Id,Nama,Spesifikasi,Tahun,Harga,Jumlah,Satuan,Kondisi;
    Button tambah;
    ImageView img_item_photo;

    AppDatabase database = Room.databaseBuilder (this, AppDatabse.class)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
