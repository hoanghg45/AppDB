package com.example.appdb.Others;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appdb.Model.Country;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "CountryManger";
        private static final int VERSION = 1;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE Country (ID INTEGER primary key AUTOINCREMENT,NAME VARCHAR(255),ADDRESS VARCHAR(255),DORF VARCHAR(255),COUNTRY VARCHAR(255),ZIPCODE INTEGER)";
        db.execSQL(SQLQuery);    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public ArrayList<Country> getAllCountry() {

        ArrayList<Country> countries = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Country", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int countryId = cursor.getInt(0);
            String countryName = cursor.getString(1);
            String countryAddress = cursor.getString(2);
            String countryDorF = cursor.getString(3);
            String countryCountry = cursor.getString(4);
            int countryZipcode = cursor.getInt(5);


            countries.add(new Country(countryId,countryName,countryAddress,countryDorF,countryCountry,countryZipcode));
            cursor.moveToNext();
        }

        cursor.close();

        return countries;
    }public Country getCountryByID(int ID) {
        Country country = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Country where id = ?", new String[]{ID + ""});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int countryId = cursor.getInt(0);
            String countryName = cursor.getString(1);
            String countryAddress = cursor.getString(2);
            String countryDorF = cursor.getString(3);
            String countryCountry = cursor.getString(4);
            int countryZipcode = cursor.getInt(5);

            country = new Country(countryId,countryName,countryAddress,countryDorF,countryCountry,countryZipcode);
        }
        cursor.close();
        return country;
    }

    //cập nhật
    public void updateCountry(Country country) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Country SET name=?, address = ?, dorf = ?,country =?,zipcode =? where id = ?",
                new String[]{country.getName(),country.getAdd(),country.getDorF(), country.getCountry(),country.getZipcode()+ "",country.getId()+""});
    }

    //thêm mới
    public void insertCountry(Country country) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Country ( name, address, dorf,country,zipcode ) VALUES (?,?,?,?,?)",
                new String[]{country.getName(),country.getAdd(),country.getDorF(), country.getCountry(),country.getZipcode()+""});
    }

    //Xoá SV bằng ID
    public void deleteCountryByID(int countryId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Country where id = ?", new String[]{String.valueOf(countryId)});
    }
    //Get item IDS



}
