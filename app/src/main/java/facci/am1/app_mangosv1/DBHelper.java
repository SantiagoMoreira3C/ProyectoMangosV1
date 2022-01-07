package facci.am1.app_mangosv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {

        //TABLA MAESTRA, USUARIOS CON EL CAMPO DE NOMBRE DE USUARIO Y LA CONTRASEÑA
        //USUARIO DESEA ALGO QUE SEA SENCILLO DE RECORDAR PARA ALMACENAR SUS PRODUCTOS

        myDB.execSQL("create table users(username text primary key, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {

    }

    public Boolean inserData(String username, String password){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        if( result == -1){
            return false;
        }else{
            return true;

        }
    }

    public Boolean checkusername(String username)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select*from users where username = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        } else{
            return false;
        }
    }
    public Boolean checkusernamePassword(String username, String passowrd){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select*from users where username = ? and password = ?", new String[] {username, passowrd});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
