package com.example.sirsitalaxmienterprises;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InscanActivity extends AppCompatActivity {
    public static EditText et_date,et_conno;
    String[] conarray=new String[50];
TextView tv_conno;
ImageView datepicker_img;
FirebaseDatabase rootNode;
DatabaseReference reference;
Calendar calendar = Calendar.getInstance();
private int year,date,month;
DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscan);
        et_date = findViewById(R.id.et_date);
        et_conno = findViewById(R.id.et_conno);
        tv_conno = findViewById(R.id.tv_conno);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = sdf.format(new Date());
        et_date.setText(cur_date);
        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_conno.getText().toString())) {
                    if (!TextUtils.isEmpty(et_date.getText().toString())) {
                        String date = et_date.getText().toString();
                        String conno_et = et_conno.getText().toString();
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("Inscan");
                        InscanHelperClass helperClass = new InscanHelperClass(date, conno_et);
                        reference.child(conno_et).setValue(helperClass);
                        et_conno.setText("");
                    } else {
                        tv_conno.setText(R.string.emptydate);
                    }
                } else {
                    tv_conno.setText(R.string.emptyconno);
                }
            }
        });
        findViewById(R.id.connosearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), BarcodeScanner.class));
            }
        });
        findViewById(R.id.datepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                date = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(InscanActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, date);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String Date = dayOfMonth + "/" + month + "/" + year;
                et_date.setText(Date);
            }
        };
    }

}