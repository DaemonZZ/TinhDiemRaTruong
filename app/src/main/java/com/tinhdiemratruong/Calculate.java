package com.tinhdiemratruong;

import android.util.Log;

import java.util.ArrayList;

public class Calculate {
    public static final float TB = 2.0f;
    public static final float Kha = 2.5f;
    public static final float Gioi = 3.2f;
    public static final float XuatSac = 3.6f;
    public Calculate() {
    }
    public ArrayList<BoDiem> tinhDiem(BoDiem diem, int tongTC, float muctieu){
        ArrayList<BoDiem> list = new ArrayList<>();
        float tongMucTieu = muctieu * tongTC;
        int soTcConLai = tongTC - (diem.getA()+diem.getB()+diem.getC()+diem.getD()+diem.getF());
        float diemDaDat = diem.getA()*4 + diem.getB()*3 + diem.getC()*2 + diem.getD();
        float diemConThieu = tongMucTieu - diemDaDat;

        for(int a = 0;a<=soTcConLai;a++)
            for(int b =0; b<=soTcConLai-a;b++)
                for(int c = 0;c<=(soTcConLai-a-b);c++)
                {
                    int d = soTcConLai - (a+b+c);
                    float sum=4*a+3*b+2*c+d;
                    if(sum>=diemConThieu&&sum<diemConThieu+4) list.add(new BoDiem(a,b,c,d,0));
                }
        Log.e("DCT",diemConThieu+"");
        //Collections.sort(list);
        return list;
    }
}
