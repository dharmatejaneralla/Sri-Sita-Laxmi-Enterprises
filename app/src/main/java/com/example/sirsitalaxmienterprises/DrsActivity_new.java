package com.example.sirsitalaxmienterprises;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DrsActivity_new extends AppCompatActivity {
public static  EditText et_drs_new_drsno;
    EditText et_date_drs_new;
ImageView datepicker_drs_new,drs_search_new;
CodeScanner mcodeScanner;
Spinner areas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drs_new);
        SimpleDateFormat simpledate = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = simpledate.format(new Date());
        et_date_drs_new = findViewById(R.id.et_new_date);
        et_date_drs_new.setText(cur_date);
        et_drs_new_drsno=findViewById(R.id.et_drsno_new);
        areas = findViewById(R.id.areas);
        findViewById(R.id.drssearch_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrsActivity_new.this,BarcodeScanner_Drs.class));
            }
        });
        final CodeScannerView scannerView = findViewById(R.id.barcodescan_new);
        mcodeScanner = new CodeScanner(this,scannerView);
        mcodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                DrsActivity_new.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String area  = areas.getSelectedItem().toString();
                        String date = et_date_drs_new.getText().toString();
                        String drsno = et_drs_new_drsno.getText().toString();
                        Toast.makeText(DrsActivity_new.this, result.getText()+"Added", Toast.LENGTH_SHORT).show();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drs");
                        Drshelperclass drshelperclass = new Drshelperclass(date,drsno,result.getText(),area);
                        reference.child(result.getText()).setValue(drshelperclass);
                        mcodeScanner.startPreview();
                    }
                });


                 }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcodeScanner.startPreview();
            }
        });
    }
}