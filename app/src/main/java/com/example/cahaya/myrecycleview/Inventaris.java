package com.example.cahaya.myrecycleview;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName="Inventaris")

class Inventaris implements Parcelable {
        @PrimaryKey
        private String id;
        @ColumnInfo
        private String nama, photo, spesifikasi, satuan, kondisi;
        @ColumnInfo
        private int jumlah, tahun, harga;

        public Inventaris(String id, String nama, int jumlah, String photo, String spesifikasi, int tahun,
                          int harga, String satuan, String kondisi ) {
            this.id = id;
            this.nama = nama;
            this.jumlah = jumlah;
            this.photo = photo;
            this.spesifikasi = spesifikasi;
            this.tahun = tahun;
            this.harga = harga;
            this.satuan = satuan;
            this.kondisi = kondisi;
        }

        public String getId() {
        return id;
    }

        public void setId(String id) { this.id = id; }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) { this.nama = nama; }

        public int getJumlah() {
        return jumlah;
    }

        public void setJumlah(int jumlah) {
            this.jumlah = jumlah;
        }

        public String getPhoto() { return photo; }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getSpesifikasi() { return spesifikasi;}

        public void setSpesifikasi(String spesifikasi) {
        this.spesifikasi = spesifikasi;
    }

        public int getTahun() {return tahun; }

        public void setTahun(int tahun) {
        this.tahun = tahun;
    }

        public int getHarga() { return harga; }

        public void setHarga(int harga) {
        this.harga = harga;
    }

        public String getSatuan() { return satuan; }

        public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

        public String getKondisi() { return kondisi; }

        public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel desti, int flags) {
        desti.writeString(this.id);
        desti.writeString(this.nama);
        desti.writeInt(this.jumlah);
        desti.writeString(this.photo);
        desti.writeString(this.spesifikasi);
        desti.writeInt(this.tahun);
        desti.writeInt(this.harga);
        desti.writeString(this.satuan);
        desti.writeString(this.kondisi);
    }

    protected Inventaris(Parcel in) {
        this.id = in.readString();
        this.nama = in.readString();
        this.jumlah = in.readInt();
        this.photo = in.readString();
        this.spesifikasi = in.readString();
        this.tahun = in.readInt();
        this.harga = in.readInt();
        this.satuan = in.readString();
        this.kondisi = in.readString();
    }

    public static final Parcelable.Creator<Inventaris> CREATOR = new Parcelable.Creator<Inventaris>() {
        @Override
        public Inventaris createFromParcel(Parcel source) {
            return new Inventaris(source);
        }

        @Override
        public Inventaris[] newArray(int size) {
            return new Inventaris[size];
        }
    };
}
