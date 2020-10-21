package com.example.sirsitalaxmienterprises;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AutoBarcodescanner extends AppCompatActivity {
    private CodeScanner mcodescanner;
    EditText tv_date;
    DatePickerDialog.OnDateSetListener setListener;
    Calendar calendar;
    Spinner couiernames_inscan;
int year,month,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_barcodescanner);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = sdf.format(new Date());
        CodeScannerView scannerView = findViewById(R.id.barcodescan);
        tv_date = findViewById(R.id.et_new_date);
        tv_date.setText(cur_date);
        couiernames_inscan = findViewById(R.id.courier_branch_inscan_new);
        mcodescanner = new CodeScanner(this, scannerView);
        mcodescanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                AutoBarcodescanner.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!TextUtils.isEmpty(result.getText())) {
                            Toast.makeText(AutoBarcodescanner.this, result.getText(), Toast.LENGTH_SHORT).show();
                            String date = tv_date.getText().toString();
                            String courier = couiernames_inscan.getSelectedItem().toString();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Inscan");
                            HelperClass_Barcodenew helperClass = new HelperClass_Barcodenew(date, result.getText(), courier);
                            reference.child(result.getText()).setValue(helperClass);
                            mcodescanner.startPreview();
                        }
                    }
                });

            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcodescanner.startPreview();
            }
        });
        findViewById(R.id.datepicker_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                date = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AutoBarcodescanner.this,
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
                tv_date.setText(Date);
            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        mcodescanner.startPreview();
    }

    @Override
    protected void onPause() {
        mcodescanner.releaseResources();
        super.onPause();
    }

}