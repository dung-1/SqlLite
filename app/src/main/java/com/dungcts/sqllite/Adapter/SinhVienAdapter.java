package com.dungcts.sqllite.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dungcts.sqllite.Activity.UpdateActivity;
import com.dungcts.sqllite.Activity.ViewActivity;
import com.dungcts.sqllite.Model.SinhVien;
import com.dungcts.sqllite.R;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    ViewActivity context;
    List<SinhVien>sinhViens;

    public SinhVienAdapter(ViewActivity context, List<SinhVien> sinhViens) {
        this.context = context;
        this.sinhViens = sinhViens;
    }

    @Override
    public int getCount() {
        return sinhViens.size();
    }

    @Override
    public Object getItem(int i) {
        return sinhViens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHoder{
        TextView txtemail;
        ImageView imgedit,imgdelete;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        ViewHoder viewHoder;
        if (view == null) {
            viewHoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_view, null);
            viewHoder.imgedit = (ImageView) view.findViewById(R.id.imgedit);
            viewHoder.imgdelete = (ImageView) view.findViewById(R.id.imgdelete);
            viewHoder.txtemail = (TextView) view.findViewById(R.id.txtEmail);
            view.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) view.getTag();
        }
        final SinhVien sinhVien = sinhViens.get(i);
        viewHoder.txtemail.setText(sinhVien.getEmail());
        viewHoder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa :"+sinhVien.getEmail()+" ra khỏi danh sách không");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.deletesv(sinhVien.getId());
                    }
                });
                builder.setNeutralButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        viewHoder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("ttupdate",sinhViens.get(i));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
