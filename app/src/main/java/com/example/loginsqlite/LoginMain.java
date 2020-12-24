package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginMain extends AppCompatActivity {

    EditText username,password;
    Button login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        username=(EditText) findViewById(R.id.tv_usernamelogin);
        password=(EditText) findViewById(R.id.tv_passwordlogin);
        login=(Button) findViewById(R.id.btn_login);
        DB=new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginMain.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginMain.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginMain.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}