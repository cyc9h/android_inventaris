package com.example.cahaya.myrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Inventaris> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>() ;
        list.addAll(InventarisData.getListData());

        showRecyclerCardView();






//        Button btn = (Button) findViewById(R.id.btn_detail);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(i);
//            }
//        });
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

    private void pindah(View v){

    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewInventarisAdapter cardViewPresidentAdapter = new CardViewInventarisAdapter(this);
        cardViewPresidentAdapter.setListInventaris(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);

    }
    private void showSelectedInventaris(Inventaris inventaris){
        Toast.makeText(this, "Kamu memilih "+inventaris.getNama(), Toast.LENGTH_SHORT).show();
    }


}

