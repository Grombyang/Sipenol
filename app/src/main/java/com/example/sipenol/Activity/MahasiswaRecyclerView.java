package com.example.sipenol.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sipenol.Adapter.MahasiswaAdapter;
import com.example.sipenol.Api;
import com.example.sipenol.Entitas.Mahasiswa;
import com.example.sipenol.GlobalFunction;
import com.example.sipenol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaRecyclerView extends AppCompatActivity {
    private static final String TAG = "MahasiswaRecyclerView";

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycle_view,null);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView rcv = view.findViewById(R.id.recycler_view);
        rcv.setHasFixedSize(true);
        Call<List<Mahasiswa>> pdg = GlobalFunction.getInstance().initApi().getAllPedagang();
        final ProgressDialog pd = GlobalFunction.getInstance().generateProgressDialog(MahasiswaRecyclerView.this);
        pd.show();
        pdg.enqueue(new Callback<List<Mahasiswa>>() {
            @Override
            public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                pd.dismiss();
                if(response.code() == Api.HTTP_OK){
                    final List<Mahasiswa> pedagangs = response.body();
                    MahasiswaAdapter pda = new MahasiswaAdapter(pedagangs, new MahasiswaAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            //pergi ke inten lain
                            Toast.makeText(MahasiswaRecyclerView.this, "coba", Toast.LENGTH_SHORT).show();
//                            Pelanggan pelanggan = (((PelangganMainNav)getActivity()).getCurrentPelanggan());
//                            Intent intent = new Intent(getActivity(), ListDagangan.class);
//                            intent.putExtra("idPedagang", pedagangs.get(position).getKodePdg());
//                            intent.putExtra("idPelanggan",pelanggan.getmUserid());
//                            startActivityForResult(intent, 990);
                        }
                    });
                    rcv.setAdapter(pda);
                }
            }

            @Override
            public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(MahasiswaRecyclerView.this, "Terjadi Kesalahan saat komunikasi dengan server!!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 990) {
//            if (resultCode == Api.RESULT_OK) {
//                ((MahasiswaRecyclerView) MahasiswaRecyclerView.this).setmUser_id(data.getStringExtra("user_id"));
//                ((MahasiswaRecyclerView) MahasiswaRecyclerView.this).changeFragment(Api.FRAGMENT_PESANAN_PELANGGAN);
//            }
//        }
//    }
}
