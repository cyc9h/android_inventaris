package com.example.cahaya.myrecycleview;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cahaya.myrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CardViewInventarisAdapter.OnItemClick{
    ProgressBar pbInvent;
    private RecyclerView rvCategory;
    private ArrayList<Inventaris> list;
    CardViewInventarisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);


        adapter = new CardViewInventarisAdapter();
        adapter.setHandler(this);




          Button btn = (Button) findViewById(R.id.btn_detail);
          btn.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
               startActivity(i);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = null;
        switch (item.getItemId()){
           // case R.id.action_list:
                //title = "Segarkan";
                //showRecyclerList();
                //break;

           // case R.id.action_grid:
                //showRecyclerGrid();
                //title = "Tambah";
               // break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void showSelectedInventaris(Inventaris inventaris){
        Toast.makeText(this, "Kamu memilih "+inventaris.getNama(), Toast.LENGTH_SHORT).show();
    }

    private void ambilData() {

        rvCategory.setVisibility(View.INVISIBLE);
        pbInvent.setVisibility(View.VISIBLE);

        if(isConnected()) {
            Api client = (new Retrofit.Builder()
                    .baseUrl("https://localhost/inventaris/public")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build())
                    .create(Api.class);

            Call<List<Inventaris>> call = client.getInventaris();

            call.enqueue(new Callback<List<Inventaris>>() {
                @Override
                public void onResponse(Call<List<Inventaris>> call, Response<List<Inventaris>> response) {
                    List<Inventaris> data = response.body();
                    Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    ArrayList<Inventaris> listinvent = new ArrayList<Inventaris>(data);
                    adapter.setListInventaris(listinvent);
                    rvCategory.setAdapter(adapter);

                    //saveGaleryData(listinvent);

                    pbInvent.setVisibility(View.INVISIBLE);
                    rvCategory.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<List<Inventaris>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Gagal coy", Toast.LENGTH_SHORT).show();
                    pbInvent.setVisibility(View.INVISIBLE);
                    rvCategory.setVisibility(View.VISIBLE);
                }
            });
        }/*else {
            Toast.makeText(MainActivity.this,"tidak konek",Toast.LENGTH_LONG).show();
            List<RomGalery> galeryRom = dbase.getGaleryDao().getGalery();
            List<Galery> galeriModels = new ArrayList<>();

            for (RomGalery rg: galeryRom ){
                Galery galeryModel = new Galery(
                        rg.id,
                        rg.nama,
                        rg.lokasi,
                        rg.gambar_url,
                        rg.deskripsi,
                        rg.lat,
                        rg.lng
                );
                galeriModels.add(galeryModel);
            }

            adapter.setListGalery(new ArrayList<Galery>(galeriModels));
            rvGalery.setAdapter(adapter);

            pbGalery.setVisibility(View.INVISIBLE);
            rvGalery.setVisibility(View.VISIBLE);

        }*/

    }
    public Boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void click(Inventaris m) {

    }
}

