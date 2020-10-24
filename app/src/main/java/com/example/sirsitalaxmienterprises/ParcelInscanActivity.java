package com.example.sirsitalaxmienterprises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.InternalTokenResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParcelInscanActivity extends AppCompatActivity {
EditText et_parcelinscan_date,et_parcelinscan_wt,et_parcelinscan_pcs;
TextView tv_error;
public static EditText et_parcelinscan_conno;
Spinner courier,wtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_inscan);
        et_parcelinscan_conno = findViewById(R.id.et_conno_parcelinscan);
        et_parcelinscan_date = findViewById(R.id.et_date_parcelinscan);
        et_parcelinscan_pcs = findViewById(R.id.et_pcs_parcelinscan);
        et_parcelinscan_wt = findViewById(R.id.et_wt_parcelinscan);
        tv_error = findViewById(R.id.tv_error_parcelinscan);
        wtname = findViewById(R.id.wtname);
        courier = findViewById(R.id.courier_parcel);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = sdf.format(new Date());
        et_parcelinscan_date.setText(cur_date);
        findViewById(R.id.save_btn_parcelinscan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_parcelinscan_date.getText().toString())) {
                    if (!TextUtils.isEmpty(et_parcelinscan_conno.getText().toString())) {
                        if (!TextUtils.isEmpty(et_parcelinscan_pcs.getText().toString())) {
                            if (!TextUtils.isEmpty(et_parcelinscan_wt.getText().toString())) {
                                String date = et_parcelinscan_date.getText().toString();
                                String conno = et_parcelinscan_conno.getText().toString();
                                String pcs = et_parcelinscan_pcs.getText().toString();
                                String  overwt = et_parcelinscan_wt.getText().toString();
                                String couriername = courier.getSelectedItem().toString();
                                String wtnames = wtname.getSelectedItem().toString();
                                String wt = overwt+"\t"+wtnames;
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ParcelInscan");
                                ParcelInscanHelperClass helperClass = new ParcelInscanHelperClass(date,conno,pcs,wt,couriername);
                                reference.child(conno).setValue(helperClass);
                                et_parcelinscan_conno.setText("");
                                et_parcelinscan_pcs.setText("");
                                et_parcelinscan_wt.setText("");
                            }
                            else {
                                tv_error.setText(R.string.emptywt);
                                tv_error.setTextColor(getResources().getColor(R.color.red));
                            }
                        }
                        else {
                            tv_error.setText(R.string.emptypcs);
                            tv_error.setTextColor(getResources().getColor(R.color.red));
                        }
                    }
                    else
                        {
                            tv_error.setText(R.string.emptyconno);
                            tv_error.setTextColor(getResources().getColor(R.color.red));
                        }
                }

                else {
                    tv_error.setText(R.string.emptydate);
                    tv_error.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
        findViewById(R.id.connosearch_parcelinscan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParcelInscanActivity.this,ParcelInscanConnoScan.class));
            }
        });

    }

}