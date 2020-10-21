package com.example.sirsitalaxmienterprises;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.Utilities;

public class SearchActivity extends AppCompatActivity {
TextView result_tv_inscan,result_tv_drsdetails,tv_pod,tv_conno_search;
EditText search_conno;
//ProgressBar progressBar;
//ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_conno = findViewById(R.id.conno_et_search);
        result_tv_inscan = findViewById(R.id.tv_result_details_inscan);
        result_tv_drsdetails = findViewById(R.id.tv_result_details_drs);
        //progressBar = findViewById(R.id.progress);
        //progressDialog = new ProgressDialog(SearchActivity.this);
        tv_conno_search = findViewById(R.id.conno_search);
        //progressDialog.setMessage("Searching");
        tv_pod = findViewById(R.id.tv_result_details_pod);
        findViewById(R.id.search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(search_conno.getText().toString())) {
//                    progressDialog.show();
//                    progressBar.setVisibility(View.VISIBLE);
                    final String con = search_conno.getText().toString();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Inscan");
                    Query chechconno = reference.orderByChild("conno").equalTo(con);
                    DatabaseReference drs_reference = FirebaseDatabase.getInstance().getReference("Drs");
                    Query checkdrs = drs_reference.orderByChild("conno").equalTo(con);
                    DatabaseReference pod_reference = FirebaseDatabase.getInstance().getReference("Pod");
                    Query checkpod = pod_reference.orderByChild("conno").equalTo(con);
                    String tvconno = "Tracking Details of"+con;
                    tv_conno_search.setText(tvconno);
                    tv_conno_search.setTextColor(getResources().getColor(R.color.blue_violet));
                    chechconno.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String datefromdb = dataSnapshot.child(con).child("date").getValue(String.class);
                                String couriername = dataSnapshot.child(con).child("couriername").getValue(String.class);
                                String details = "RecievedDate: " + datefromdb +"\nCourier:\t  "+couriername;
                                result_tv_inscan.setText(details);
                                result_tv_inscan.setTextColor(getResources().getColor(R.color.green));
                            }
                            else
                            {
                                    result_tv_inscan.setText(R.string.detailsnotfound);
                                    result_tv_inscan.setTextColor(getResources().getColor(R.color.red));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    checkdrs.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String drsdate = dataSnapshot.child(con).child("drsdate").getValue(String.class);
                                String drsno = dataSnapshot.child(con).child("drsno").getValue(String.class);
                                String area = dataSnapshot.child(con).child("area").getValue(String.class);
                                String drs_details = "Drs Date:" + drsdate + "\nDrs No:  " + drsno +"\nArea:    "+area;
                                result_tv_drsdetails.setText(drs_details);
                                result_tv_drsdetails.setTextColor(getResources().getColor(R.color.blue));
                            }
                            else {
                                result_tv_drsdetails.setText(R.string.detailsnotfound);
                                result_tv_drsdetails.setTextColor(getResources().getColor(R.color.red));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    checkpod.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String deldate = dataSnapshot.child(con).child("deldate").getValue(String.class);
                                String recname = dataSnapshot.child(con).child("recname").getValue(String.class);
                                String pod_details = "Delivery Date:  " + deldate+ "\nReciever Name: " + recname;
                                tv_pod.setText(pod_details);
                                tv_pod.setTextColor(getResources().getColor(R.color.dark_orchid));
                            }
                            else {
                                tv_pod.setText(R.string.detailsnotfound);
                                tv_pod.setTextColor(getResources().getColor(R.color.red));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
//                    progressBar.setProgress(30);
//                    if (progressBar.getProgress() == 30) {
//                        progressBar.setVisibility(View.GONE);
//                    }

                }
                else
                    {
                    result_tv_inscan.setText(R.string.search_conno_empty);
                    result_tv_inscan.setTextColor(getResources().getColor(R.color.red));
                    }
            }
        });

    }
}