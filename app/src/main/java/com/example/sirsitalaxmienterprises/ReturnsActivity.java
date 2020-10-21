package com.example.sirsitalaxmienterprises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReturnsActivity extends AppCompatActivity {
public static EditText returns_et_date,returns_et_conno,returns_et_msfno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returns);
        returns_et_date = findViewById(R.id.et_date_returns);
        returns_et_conno = findViewById(R.id.et_conno_returns);
        returns_et_msfno = findViewById(R.id.et_msfno_returns);
        final Spinner reason = findViewById(R.id.returnreasons);
        findViewById(R.id.save_btn_returns).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returndate = returns_et_date.getText().toString();
                String returnmsfno = returns_et_msfno.getText().toString();
                String returnsreason = reason.getSelectedItem().toString();
                String returnconno  = returns_et_conno.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Returns");
                ReturnHelperClass helperClass = new ReturnHelperClass(returndate,returnmsfno,returnconno,returnsreason);
                reference.child(returnconno).setValue(helperClass);
                Toast.makeText(ReturnsActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                returns_et_conno.setText("");
            }
        });
        findViewById(R.id.close_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returns_et_msfno.setText("");
            }
        });
        findViewById(R.id.connosearch_returns).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReturnsActivity.this,ReturnsConnoScanner.class));
            }
        });
        findViewById(R.id.msfnosearch_returns).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReturnsActivity.this,ReturnsMsfnoScanner.class));
            }
        });
    }
}