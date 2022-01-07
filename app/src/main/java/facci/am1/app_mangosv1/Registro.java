package facci.am1.app_mangosv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    EditText username, password, repassword;
    Button btn_signup, btn_signing;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signing = (Button) findViewById(R.id.btn_signin);

        myDB = new DBHelper(this);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") ||  pass.equals("") || repass.equals("")){
                    Toast.makeText(Registro.this, "Los campos se encuentran vacíos", Toast.LENGTH_SHORT).show();

                }else {

                    if(pass.equals(repass)){
                        Boolean usercheckResult = myDB.checkusername(user);
                        if(usercheckResult == false){
                           Boolean regResult = myDB.inserData(user,pass);
                           if(regResult == true){
                               Toast.makeText(Registro.this, "Registro éxito", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                               startActivity(intent);

                           }else{
                               Toast.makeText(Registro.this, "Registro fallido", Toast.LENGTH_SHORT).show();

                           }
                        }else{
                            Toast.makeText(Registro.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(Registro.this, "Contraseña no encontrada", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        btn_signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}