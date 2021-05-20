package com.tinhdiemratruong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class KetQuaFragment extends Fragment {
    Context c = this.getContext();
    ArrayList<BoDiem> list = new ArrayList<>();
    RecyclerView rv;
    AdapterKQ adapterKQ;
    Spinner spinner;
    BoDiem input;
    int TongTC;
    int muctieu; //Vi tri dropdown

    public static KetQuaFragment newInstance(String param1, String param2) {
        KetQuaFragment fragment = new KetQuaFragment();

        return fragment;
    }
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_ket_qua,container,false);
       rv = view.findViewById(R.id.rvKQ);


        Intent intent = this.getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle !=null){
            list = (ArrayList<BoDiem>) bundle.getSerializable("list");
            input = (BoDiem) bundle.getSerializable("BoDiem");
            TongTC = bundle.getInt("TongTC");
            muctieu = bundle.getInt("MucTieu");
        }

        adapterKQ = new AdapterKQ(list,c);
        LinearLayoutManager lm = new LinearLayoutManager(c);
        rv.setAdapter(adapterKQ);
        rv.setLayoutManager(lm);

        spinner = view.findViewById(R.id.dropdownKQ);
       String[] items = new String[]{"Trung Bình", "Khá", "Giỏi","Xuất sắc"};
       ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
       spinner.setAdapter(adapter);
       spinner.setSelection(muctieu);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Calculate cal = new Calculate();
               switch (position){
                   case 0 :
                       list = cal.tinhDiem(input,TongTC,Calculate.TB);
                       break;
                   case 1 :
                       list = cal.tinhDiem(input,TongTC,Calculate.Kha);
                       break;
                   case 2 :
                       list = cal.tinhDiem(input,TongTC,Calculate.Gioi);
                       break;
                   case 3 :
                       list = cal.tinhDiem(input,TongTC,Calculate.XuatSac);
                       break;
                   default:
                       throw new IllegalStateException("Unexpected value: " + position);
               }
               rv.setAdapter(null);
               adapterKQ = new AdapterKQ(list,c);
               rv.setAdapter(adapterKQ);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


       return view;
    }
}
