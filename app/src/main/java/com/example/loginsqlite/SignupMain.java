package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignupMain extends AppCompatActivity {

    EditText username,password,retypepassword;
    Button signup;
    TextView tvlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_main);

        username=(EditText) findViewById(R.id.tv_username);
        password=(EditText) findViewById(R.id.tv_password);
        retypepassword=(EditText) findViewById(R.id.tv_repassword);
        signup=(Button) findViewById(R.id.btn_signup);
        tvlogin=(TextView) findViewById(R.id.tv_login);
        DB=new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String retypass=retypepassword.getText().toString();

                if(user.equals("") || pass.equals("") || retypass.equals("") )
                    Toast.makeText(SignupMain.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(retypass)){
                        Boolean checkuser=DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(SignupMain.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),LoginMain.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SignupMain.this,"Đăng kí thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SignupMain.this,"Tên đăng nhập đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignupMain.this,"Mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginMain.class);
                startActivity(intent);
            }
        });

    }
}