package facci.am1.app_mangosv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private EditText et_codigoart, et_descripcionart, et_precioart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        et_codigoart = (EditText) findViewById(R.id.txt_codigoart);
        et_descripcionart = (EditText) findViewById(R.id.txt_descripcionart);
        et_precioart = (EditText) findViewById(R.id.txt_precioart);

    }

    //Método para dar alta los productos
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigoart = et_codigoart.getText().toString();
        String descripcionart = et_descripcionart.getText().toString();
        String precioart = et_precioart.getText().toString();

        if(!codigoart.isEmpty() && !descripcionart.isEmpty() && !precioart.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigoart", codigoart);
            registro.put("descripcionart", descripcionart);
            registro.put("precioart",precioart);

            BaseDeDatabase.insert("articulos",null, registro);

            BaseDeDatabase.close();
            et_codigoart.setText("");
            et_descripcionart.setText("");
            et_precioart.setText("");

            Toast.makeText(this, "Registro éxitoso", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "DEbes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

        //Método para consultar un  artículo o producto

        public void Buscar(View view){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
            SQLiteDatabase BaseDeDatabase= admin.getWritableDatabase();

            String codigo = et_codigoart.getText().toString();

            if(!codigo.isEmpty()){
                Cursor fila = BaseDeDatabase.rawQuery("select descripcionart, precioart from articulos where codigoart ="+ codigo, null);
                if (fila.moveToFirst()){
                    et_descripcionart.setText(fila.getString(0));
                    et_precioart.setText(fila.getString(1));
                    BaseDeDatabase.close();
                }else{
                    Toast.makeText(this, "No existe el articulo", Toast.LENGTH_SHORT).show();
                    BaseDeDatabase.close();
                }

            }else {

                Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();

            }


        }


        //Metodo eliminar articulo o producto
        public void Eliminar (View view){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();
            
            String codigo = et_codigoart.getText().toString();
            
            if (!codigo.isEmpty()){
                int cantidad = BaseDeDatabase.delete("articulos", "codigoart="+ codigo, null);
                BaseDeDatabase.close();

                et_codigoart.setText("");
                et_descripcionart.setText("");
                et_precioart.setText("");
                if(cantidad == 1){
                    Toast.makeText(this, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "Debes introducir el código del articulo", Toast.LENGTH_SHORT).show();
            }
        }

        //Metodo para actualizar un articulo o producto
        public void Modificar(View view){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

            String codigo = et_codigoart.getText().toString();
            String descripcion = et_descripcionart.getText().toString();
            String precio = et_precioart.getText().toString();
            if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
                ContentValues registro = new ContentValues();
                registro.put("codigoart", codigo);
                registro.put("descripcionart", descripcion);
                registro.put("precioart", precio);

                int cantidad = BaseDeDatabase.update("articulos", registro, "codigoart="+codigo,null);
                    BaseDeDatabase.close();

                if(cantidad == 1){
                    Toast.makeText(this, "Articulo ha sido modificado correctamente", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        }





}