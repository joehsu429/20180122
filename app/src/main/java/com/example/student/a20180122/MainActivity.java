package com.example.student.a20180122;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //m1
    public void click1(View v) {
        File dbFile = new File(getFilesDir(), "student.db");
        InputStream is = getResources().openRawResource(R.raw.student);
        try {
            OutputStream os=new FileOutputStream(dbFile);
            int r;
            while((r=is.read()) !=-1)
            {
                os.write(r);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //m2
    public void click2(View v)
    {
        File dbfile=new File(getFilesDir(),"student.db");
        SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c=db.rawQuery("Select * from students",null);
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," +c.getInt(2));//讀取id,讀取分數
//        c.moveToNext();//讀取下一個
//        Log.d("DB",c.getString(1) + "," +c.getInt(2));
        while(c.moveToNext())//在m4的用法
        {
            Log.d("DB",c.getString(1) + "," +c.getInt(2));
        }

    }
    public void click3(View v)
    {
        File dbfile=new File(getFilesDir(),"student.db");
        SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        String strSql="Select * from students where _id=?";
        Cursor c=db.rawQuery(strSql,new String[]{"2"});
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," +c.getInt(2));
    }
    public void click4(View v)//SQLite自己的用法
    {
        File dbfile=new File(getFilesDir(),"student.db");
        SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c=db.query("students",new String[]{"_id","name","score"},null,null,null,null,null);
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," +c.getInt(2));
    }
    public void click5(View v)
    {
        File dbfile=new File(getFilesDir(),"student.db");
        SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c=db.query("students",new String[]{"_id","name","score"},"_id=?",new String[]{"1"},null,null,null);
        c.moveToFirst();
        Log.d("DB",c.getString(1) + "," +c.getInt(2));
    }
    public void click6(View v)//新增 只能按一次 先按btn6再按btn2
    {
        File dbfile=new File(getFilesDir(),"student.db");
        SQLiteDatabase db=SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("Insert into students(_id,name,score)values(3,'cc',55)");
        db.close();
    }
    public void click7(View v)//新增 只能按一次
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("_id", 4);
        cv.put("name", "dd");
        cv.put("score", 15);
        db.insert("students", null, cv);
        db.close();
    }
    public void click8(View v)//af1 修改
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("name", "dd");
        db.update("students",cv,"_id=?",new String[]{"4"});
        db.close();
    }
    public void click9(View v)//af1 刪除
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.delete("students", "_id=?", new String[] {"4"});
        db.close();
    }
}