package com.example.cahaya.myrecycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cahaya.myrecyclerview.R;

import java.util.ArrayList;

public class CardViewInventarisAdapter extends RecyclerView.Adapter<CardViewInventarisAdapter.CardViewViewHolder>{

    private ArrayList<Inventaris>listInventaris;
    private Context context;
    OnItemClick handler;

    public void setHandler(OnItemClick clilckHandler){
        handler = clilckHandler;
    }



    public ArrayList<Inventaris> getListInventaris() {
        return listInventaris;
    }

    public void setListInventaris(ArrayList<Inventaris> listInventaris) {
        this.listInventaris = listInventaris;
    }
    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_inventaris, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {

        Inventaris p = getListInventaris().get(position);

        Glide.with(context)
                .load(p.getPhoto())
                .override(350, 550)
                .into(holder.imgPhoto);

        holder.tvId.setText(p.getId());
        holder.tvNama.setText(p.getNama());


        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite "+getListInventaris().get(position).getNama(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListInventaris().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvId, tvNama, tvJumlah;
        Button btnDetail;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView)itemView.findViewById(R.id.img_item_photo);
            tvId = (TextView)itemView.findViewById(R.id.tv_id);
            tvNama = (TextView)itemView.findViewById(R.id.tv_nama);
            tvJumlah = (TextView)itemView.findViewById(R.id.tv_jumlah);
            btnDetail = (Button)itemView.findViewById(R.id.btn_detail);
        }
    }

    public interface OnItemClick{
        void click(Inventaris m);
    }


}