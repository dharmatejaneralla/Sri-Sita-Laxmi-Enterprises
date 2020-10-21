package com.example.sirsitalaxmienterprises;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DrsActivity extends AppCompatActivity {
Calendar calendar;
int year,month,date;
TextView tv_error;
public  static EditText et_date_drs,et_drsno,et_conno;
DatePickerDialog.OnDateSetListener setListener_drs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drs);
        et_date_drs = findViewById(R.id.et_date);
        et_drsno  = findViewById(R.id.et_drsno);
        tv_error = findViewById(R.id.tv_result);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = sdf.format(new Date());
        et_date_drs.setText(cur_date);
        et_conno = findViewById(R.id.et_conno);
        findViewById(R.id.drssearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BarcodeScanner_Drs.class));
            }
        });
        findViewById(R.id.connosearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BarcodeScanner_drs_conno.class));
            }
        });
        findViewById(R.id.save_btn_drs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_date_drs.getText().toString()))
                {
                    if (!TextUtils.isEmpty(et_conno.getText().toString()))
                    {
                        if (!TextUtils.isEmpty(et_drsno.getText().toString()))
                        {
                            String drsdate = et_date_drs.getText().toString();
                            String Drsno = et_drsno.getText().toString();
                            String connno = et_conno.getText().toString();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drs");
                            Drshelperclass helperclass = new Drshelperclass(drsdate,Drsno,connno,null);
                            reference.child(connno).setValue(helperclass);
                            et_conno.setText("");
                        }
                        else {
                            tv_error.setText(R.string.drsempty);
                            tv_error.setTextColor(getResources().getColor(R.color.red));
                        }
                    }
                    else
                    {
                        tv_error.setText(R.string.emptyconno_drs);
                        tv_error.setTextColor(getResources().getColor(R.color.red));
                    }
                }
                else
                {
                    tv_error.setText(R.string.emptydate);
                    tv_error.setTextColor(getResources().getColor(R.color.red));
                }

            }
        });
        findViewById(R.id.close_btn_drs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_drsno.setText("");
            }
        });
//        findViewById(R.id.datepicker_drs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                year = calendar.get(Calendar.YEAR);
//                month = calendar.get(Calendar.MONTH);
//                date = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(DrsActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener_drs, year, month, date);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//        setListener_drs = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month + 1;
//                String Date = dayOfMonth + "/" + month + "/" + year;
//                et_date_drs.setText(Date);
//            }
//        };
    }
}