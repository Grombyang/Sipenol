package com.example.sipenol.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sipenol.Api;
import com.example.sipenol.Entitas.Mahasiswa;
import com.example.sipenol.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.PedagangViewHolder> {
    private List<Mahasiswa> mPedagang;
    private OnItemClickListener listener;

    public MahasiswaAdapter(List<Mahasiswa> mPedagang, OnItemClickListener listener) {
        this.mPedagang = mPedagang;
        this.listener = listener;
    }

    public static class PedagangViewHolder extends RecyclerView.ViewHolder {
        TextView namaPedagang, noHpPedagang;
        ImageView fotoPedagang;
        public PedagangViewHolder(View itemView){
            super(itemView);
            namaPedagang = itemView.findViewById(R.id.primary_text_view_holder);
            noHpPedagang = itemView.findViewById(R.id.secondary_text_view_holder);
            fotoPedagang = itemView.findViewById(R.id.image_view_holder);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View v, int position);
    }
    @NonNull
    @Override
    public PedagangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        //inisiasi holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        final PedagangViewHolder pdh = new PedagangViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, pdh.getPosition());
            }
        });
        return pdh;
    }

    @Override
    public void onBindViewHolder(@NonNull PedagangViewHolder viewHolder, int position) {
        //set isi dari view holder
        Mahasiswa pedagang = mPedagang.get(position);
        viewHolder.namaPedagang.setText(pedagang.getNama());
        viewHolder.noHpPedagang.setText(pedagang.getNoHp());
        Picasso.get().load(Api.PATH_TO_IMAGE+pedagang.getmProfil()).into(viewHolder.fotoPedagang);
    }

    @Override
    public int getItemCount() {
        return mPedagang.size();
    }
}
