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

public class EditActivity extends AppCompatActivity {
    public EditText edtName,edtAdd,edtZc;
    public Button btnEdt,btnDel;
    public Spinner spnCou;
    public RadioButton rbDos,rbFore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtName = findViewById(R.id.edtName);
        edtAdd = findViewById(R.id.edtAdd);
        edtZc = findViewById(R.id.edtZc);
        btnEdt = findViewById(R.id.btnEdt);
        btnDel = findViewById(R.id.btnDel);
        spnCou = findViewById(R.id.spnCou);
        rbDos = findViewById(R.id.rbDos);
        rbFore = findViewById(R.id.rbFore);
        DBManager db = new DBManager(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Country country =db.getCountryByID(id);

        edtName.setText(country.getName());
        edtZc.setText(String.valueOf(country.getZipcode()));
        edtAdd.setText(country.getAdd());
        if(country.getDorF().equals("Domestic")){
            rbDos.setChecked(true);
        }else rbFore.setChecked(true);
        switch (country.getCountry()){
            case "VietNam": spnCou.setSelection(0);
                break;
            case "UnitedKingdom": spnCou.setSelection(1);
                break;
            case "Japan": spnCou.setSelection(2);
                break;
            case "SouthKorea": spnCou.setSelection(3);
                break;
            default:
                break;
        }
        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String add = edtAdd.getText().toString();
                int zc = Integer.parseInt(edtZc.getText().toString());
                String dorf ="";
                if(rbDos.isSelected()){
                    dorf = "Domestic";
                }
                else dorf = "Foreign";
                String coun = spnCou.getSelectedItem().toString();
                Country country = new Country(id,name,add,dorf,coun,zc);

                db.updateCountry(country);
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this,MainActivity.class));
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.deleteCountryByID(id);
                Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this,MainActivity.class));
            }
        });
    }
}