package com.example.homework;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button signup_Button;
    Button login_Button;
    Button product_Button;
    EditText mEditId;
    EditText mEditPw;
    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("로그인");

        mEditId = (EditText) findViewById(R.id.login_id_input);
        mEditPw = (EditText) findViewById(R.id.login_pw_input);

        SharedPreferences prefs = getSharedPreferences("person_info", Activity.MODE_PRIVATE);
        String prefsId = prefs.getString("id", "");
        String prefsPw = prefs.getString("pw", "");
        Boolean prefsIsSave = prefs.getBoolean("isIdSave", false);

        SharedPreferences.Editor editor = prefs.edit();

        SharedPreferences login_prefs = getSharedPreferences("login_info", Activity.MODE_PRIVATE);
        SharedPreferences.Editor login_editor = login_prefs.edit();


        mEditId.setText(prefsId);

        // 로그인 버튼
        login_Button = (Button) findViewById(R.id.login_Button);

        // 로그인 버튼 클릭시, 상품 페이지로 이동
        login_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(prefsId.equals(mEditId.getText().toString())) {
                    if (prefsPw.equals(mEditPw.getText().toString())) {
                        login_editor.putBoolean("isLogin", true);
                        login_editor.putString("loginId", mEditId.getText().toString());
                        login_editor.apply();
                        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "아이디를 확인하세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //회원가입 버튼
        signup_Button = (Button) findViewById(R.id.signup_Button);

        //회원가입 버튼 클릭시, 회원가입 페이지로 이동
        signup_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        // 메인으로 버튼
        product_Button = (Button) findViewById(R.id.product_Button);

        //메인 버튼 클릭시, 상품 페이지로 이동
        product_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login_editor.putBoolean("isLogin", false);
                login_editor.remove("loginId");
                login_editor.apply();
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivity(intent);
            }
        });
    }
}