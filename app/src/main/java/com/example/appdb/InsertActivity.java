package com.example.appdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appdb.Model.Country;
import com.example.appdb.Others.DBManager;
import com.example.appdb.R;

public class InsertActivity extends AppCompatActivity {
    public EditText edtName,edtAdd,edtZc;
    public Button btnIns;
    public Spinner spnCou;
    public RadioButton rbDos,rbFore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtName = findViewById(R.id.edtName);
        edtAdd = findViewById(R.id.edtAdd);
        edtZc = findViewById(R.id.edtZc);
        btnIns = findViewById(R.id.btnIns);
        spnCou = findViewById(R.id.spnCou);
        rbDos = findViewById(R.id.rbDos);
        rbFore = findViewById(R.id.rbFore);
        DBManager dbManager = new DBManager(this);


        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id= 0;
                String name = edtName.getText().toString();
                String add = edtAdd.getText().toString();
                int zc = Integer.parseInt(edtZc.getText().toString());
                String dorf ="";
                if(rbDos.isChecked()){
                    dorf = "Domestic";
                }
                else dorf = "Foreign";
                String coun = spnCou.getSelectedItem().toString();
                Country country = new Country(id,name,add,dorf,coun,zc);

            dbManager.insertCountry(country);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InsertActivity.this,MainActivity.class));
            }
        });
    }
}