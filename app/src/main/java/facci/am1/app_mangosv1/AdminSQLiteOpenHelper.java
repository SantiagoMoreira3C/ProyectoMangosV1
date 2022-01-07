package facci.am1.app_mangosv1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Segundo parámetro BaseDeDatos

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //TABLA YA TRANSACCIONAL, ES DONDE SE CREAR LOS ARTÍCULOS PARA CADA USUARIO, ESTE CONTIENE
        //LA INFORMACIÓN RELEVANTE QUE SE REQUIERE PARA EL NEGOCIO DE LA PERSONA INDICADA,
        //POSEE UN CODIGO, UNA DESCRIPCION, Y UN PRECIO TIPO DECIMAL

        BaseDeDatos.execSQL("create table articulos(codigoart int primary key, descripcionart text, precioart real )");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
