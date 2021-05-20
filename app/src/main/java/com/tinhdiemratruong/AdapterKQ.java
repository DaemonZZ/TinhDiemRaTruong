package com.tinhdiemratruong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterKQ extends RecyclerView.Adapter {
    private ArrayList<BoDiem> list;
    private Context c;

    public AdapterKQ(ArrayList<BoDiem> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view =inflater.inflate(R.layout.item_ket_qua,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        BoDiem b = list.get(position);
        ((ViewHolder)holder).a.setText(b.getA()+"");
        ((ViewHolder)holder).b.setText(b.getB()+"");
        ((ViewHolder)holder).c.setText(b.getC()+"");
        ((ViewHolder)holder).d.setText(b.getD()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView a,b,c,d;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            a=itemView.findViewById((R.id.diemA));
            b=itemView.findViewById((R.id.diemB));
            c=itemView.findViewById((R.id.diemC));
            d=itemView.findViewById((R.id.diemD));
        }
    }
}
