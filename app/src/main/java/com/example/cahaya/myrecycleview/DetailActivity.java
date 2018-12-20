package com.example.cahaya.myrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cahaya.myrecyclerview.R;

public class DetailActivity extends AppCompatActivity {

    Inventaris invent;

    TextView Id,Nama,Spesifikasi,Tahun,Harga,Jumlah,Satuan,Kondisi;
    Button Bagikan, Ubah, Hapus;
    ImageView img_item_photo;

    private static final String DATABASE_NAME = "Inventaris";
    private AppDatabase appDatabase;

  //  AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "Inventaris").allowMainThreadQueries().build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Id =(TextView) findViewById(R.id.id);
        Nama=(TextView) findViewById(R.id.nama);
        Spesifikasi = (TextView) findViewById(R.id.spesifikasi) ;
        Tahun = (TextView) findViewById(R.id.tahun);
        Harga= (TextView) findViewById(R.id.harga);
        Jumlah= (TextView) findViewById(R.id.jumlah);
        Satuan= (TextView) findViewById(R.id.satuan);
        Kondisi= (TextView) findViewById(R.id.kondisi);
        Bagikan=(Button) findViewById(R.id.bagikan);
        Ubah=(Button) findViewById(R.id.ubah);
        Hapus=(Button) findViewById(R.id.hapus);
        img_item_photo=(ImageView) findViewById(R.id.img_item_photo);

        Glide.with(this)
                .load(invent.getPhoto())
                .into(img_item_photo);

        Id.setText(invent.getId());
        Nama.setText(invent.getNama());
        Spesifikasi.setText(invent.getSpesifikasi());
        Tahun.setText(invent.getTahun());
        Harga.setText(invent.getHarga());
        Jumlah.setText(invent.getJumlah());
        Satuan.setText(invent.getSatuan());
        Kondisi.setText(invent.getKondisi());
    }
}
