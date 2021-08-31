package com.example.appdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdb.Adapter.CountryAdapter;
import com.example.appdb.Model.Country;
import com.example.appdb.Others.DBManager;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rclCountryList;
    private CountryAdapter countryAdapter;
    private Button btnIs;
    ArrayList<Country> alCountries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rclCountryList = findViewById(R.id.rclCt);
        btnIs = findViewById(R.id.btnIs);

        //

        DBManager dbManager = new DBManager(this);
        alCountries = new ArrayList<>(dbManager.getAllCountry());
//
//        for(int i =1;i<10;i++){
//            alCountries.add(new Country(i,"Lăng Bác","HN","Domestic","VietNam",1700000));
//        }

        rclCountryList.setHasFixedSize(true);

        countryAdapter = new CountryAdapter(alCountries,MainActivity.this, new CountryAdapter.CountryAdapterListener() {
            @Override
            public void click(View v, int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();            }
        });
        /////
        Intent intent = new Intent(MainActivity.this,EditActivity.class);
        countryAdapter = new CountryAdapter(alCountries, MainActivity.this, new CountryAdapter.CountryAdapterListener() {
            @Override
            public void click(View v, int position) {


                int id = alCountries.get(position).getId();
                Country country = dbManager.getCountryByID(id);
                intent.putExtra("id",country.getId());
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rclCountryList.setLayoutManager(linearLayoutManager);
        rclCountryList.setAdapter(countryAdapter);


        btnIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsertActivity.class);
                startActivity( intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuExit:
                AlertDialog.Builder bu = new AlertDialog.Builder(MainActivity.this);
                bu.setTitle("Thông báo");
                bu.setMessage("Bạn có muốn thoát chương trình?");
                bu.setPositiveButton("Baiii", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                bu.setNegativeButton("Khum", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = bu.create();
                al.show();
                break;

            default:break;
        }


        return super.onOptionsItemSelected(item);


    }
}