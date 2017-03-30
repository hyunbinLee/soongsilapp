package com.example.hblee.map2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseManager extends SQLiteOpenHelper { //스케줄러에 저장한 스케줄을 데이터베이스를 이용하여 관리

    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCHEDULE(code INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, contents TEXT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(Schedules schedules) { //데이터 삽입
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into SCHEDULE values(null, '" + schedules.date + "', '" + schedules.title + "', '" + schedules.contents + "');");
        db.close();
    }

    public void delete(Schedules schedules) { //데이터 삭제
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from SCHEDULE where code = '" + schedules.code + "';");
    }

    public ArrayList<Schedules> select(Schedules schedules) { //db로부터 스케줄 정보를 얻어옴
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Schedules> list = new ArrayList<>();

        String str = "";

        Cursor cursor = db.rawQuery("select * from SCHEDULE where date = '" + schedules.date + "'", null);
        while(cursor.moveToNext()) {
            Schedules schedules1 = new Schedules();
            schedules1.code = cursor.getString(0);
            schedules1.date = cursor.getString(1);
            schedules1.title = cursor.getString(2);
            schedules1.contents = cursor.getString(3);

            list.add(schedules1);
        }

        return list;
    }
}
