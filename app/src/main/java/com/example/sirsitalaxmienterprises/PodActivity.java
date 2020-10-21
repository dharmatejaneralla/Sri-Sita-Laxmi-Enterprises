package com.example.sirsitalaxmienterprises;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PodActivity extends AppCompatActivity {
public static EditText et_pod_date,et_pod_conno,et_pod_recname;
TextView tv_error_pod;
Calendar calendar;
private  int year,month,date;
DatePickerDialog.OnDateSetListener setListener_pod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pod);
        et_pod_conno = findViewById(R.id.et_conno_pod);
        et_pod_date = findViewById(R.id.et_date_pod);
        et_pod_recname = findViewById(R.id.et_recievername_pod);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = sdf.format(new Date());
        et_pod_date.setText(cur_date);
        tv_error_pod = findViewById(R.id.tv_result_pod);
        findViewById(R.id.save_btn_pod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_pod_date.getText()))
                {
                    if (!TextUtils.isEmpty(et_pod_conno.getText()))
                    {
                        if (!TextUtils.isEmpty(et_pod_recname.getText()))
                        {
                            String deldate = et_pod_date.getText().toString();
                            String podconno = et_pod_conno.getText().toString();
                            String recname = et_pod_recname.getText().toString();
                            DatabaseReference reference_pod = FirebaseDatabase.getInstance().getReference("Pod");
                            PodHelperclass helperclass  = new PodHelperclass(deldate,podconno,recname);
                            reference_pod.child(podconno).setValue(helperclass);
                            et_pod_conno.setText("");
                            et_pod_recname.setText("");
                        }
                        else
                        {
                            tv_error_pod.setText(R.string.recname_empty);
                        }
                    }
                    else
                    {
                        tv_error_pod.setText(R.string.pod_con_empty);
                    }
                }
                else
                {
                    tv_error_pod.setText(R.string.empty_pod_date);
                }
            }
        });
        findViewById(R.id.connosearch_pod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BarcodeScanner_Pod.class));
            }
        });
//        findViewById(R.id.datepicker_pod).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                year = calendar.get(Calendar.YEAR);
//                month = calendar.get(Calendar.MONTH);
//                date = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(PodActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener_pod, year, month, date);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//        setListener_pod = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month = month + 1;
//                String Date = dayOfMonth + "/" + month + "/" + year;
//                et_pod_date.setText(Date);
//            }
//        };
    }
}