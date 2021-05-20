package com.tinhdiemratruong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Spinner drop;
    Button btnTinh;
    EditText atxt,btxt,ctxt,dtxt,ftxt,sumtxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drop = findViewById(R.id.dropdown);
        String[] items = new String[]{"Trung Bình", "Khá", "Giỏi","Xuất sắc"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        drop.setAdapter(adapter);

        atxt = findViewById(R.id.atxt);
        btxt = findViewById(R.id.btxt);
        ctxt = findViewById(R.id.ctxt);
        dtxt = findViewById(R.id.dtxt);
        ftxt = findViewById(R.id.ftxt);
        sumtxt = findViewById(R.id.sumtxt);
        btnTinh = findViewById(R.id.btnTinh);
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BoDiem input = new BoDiem();
                    input.setA(Integer.parseInt(atxt.getText().toString()));
                    input.setB(Integer.parseInt(btxt.getText().toString()));
                    input.setC(Integer.parseInt(ctxt.getText().toString()));
                    input.setD(Integer.parseInt(dtxt.getText().toString()));
                    input.setF(Integer.parseInt(ftxt.getText().toString()));
                    int TongTC = Integer.parseInt(sumtxt.getText().toString());
                    if(validateForm(input,TongTC)){
                        Calculate cal = new Calculate();
                        ArrayList<BoDiem> list;
                        switch (drop.getSelectedItemPosition()){
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
                                throw new IllegalStateException("Unexpected value: " + drop.getSelectedItemPosition());
                        }
                        if(list.size()>0){
                            Intent intent = new Intent(MainActivity.this,KetQuaActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("list",list);
                            bundle.putSerializable("BoDiem",input);
                            bundle.putInt("TongTC",TongTC);
                            bundle.putInt("MucTieu",drop.getSelectedItemPosition());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Không tìm được kết quả phù hợp cho mục tiêu của bạn", Toast.LENGTH_SHORT).show();
                        }


//                    for (BoDiem it:list
//                         ) {
//                        Log.e("KetQua","a: "+it.getA()+"TC\nb: "+it.getB()+"TC\nc: "+it.getC()+"TC\nd: "+it.getD()+"TC\nSum: "+it.sum()+"\n" );
//                    }
                    }
                    else{
                        Log.e("Validate","Lỗi thông sô nhập");
                        Toast.makeText(MainActivity.this,"Thông sô không phù hợp",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this,"Nhập đầy đủ thông số",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean validateForm(BoDiem b, int tong){
        //Kiem tra thong so dau vao
        if(b.getA()+b.getB()+b.getC()+b.getD()+b.getF()>=tong){
            return false;
        }

        return true;
    }
}