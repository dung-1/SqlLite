package com.dungcts.sqllite.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dungcts.sqllite.Adapter.SinhVienAdapter;
import com.dungcts.sqllite.Database.SQLiteHanderl;
import com.dungcts.sqllite.Model.SinhVien;
import com.dungcts.sqllite.R;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    SQLiteHanderl db;
    ListView lvview;
    ArrayList<SinhVien> sinhViens;
    SinhVienAdapter sinhVienAdapter;
    TextView txttong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        db =new SQLiteHanderl(getApplicationContext());
        init();
    }

    private void init() {
        lvview = (ListView)findViewById(R.id.lview);
         sinhViens = new ArrayList<>();
        sinhViens = getInfo();
        sinhVienAdapter = new SinhVienAdapter(this,sinhViens);
        lvview.setAdapter(sinhVienAdapter);
        lvview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewActivity.this,Infomation.class);
                intent.putExtra("thongtin",sinhViens.get(i));
                startActivity(intent);
            }
        });
        txttong = (TextView) findViewById(R.id.txttong);
        txttong.setText("Số lượng :"+sinhViens.size());
    }

    private ArrayList<SinhVien> getInfo() {
        Cursor cursor = db.getAllDataSV();
        ArrayList<SinhVien>sinhViens = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            String role = cursor.getString(3);
            SinhVien sinhVien = new SinhVien(id,email,password,role);
            sinhViens.add(sinhVien);
        }
        return  sinhViens;
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
    public void deletesv(String ma){
        db.delete(ma);
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        init();
    }
}
