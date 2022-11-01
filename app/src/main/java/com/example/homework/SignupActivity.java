package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    Button signup_commit;
    private EditText mEditId;
    private Button mDupButton;
    private EditText mEditPw;
    private EditText mEditName;
    private EditText mEditPhone;
    private EditText mEditAddress;
    private RadioButton mAgreeDecline;
    private RadioButton mAgreeAccept;
    private boolean isIdDup = false;
    private boolean isValid = false;

    boolean check_validation(String password) {
        // 비밀번호 유효성 검사식 : 숫자, 특수문자가 포함되어야 한다.
        String val_symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])";
        // 정규표현식 컴파일
        Pattern pattern_symbol = Pattern.compile(val_symbol);
        Matcher matcher_symbol = pattern_symbol.matcher(password);
        if (matcher_symbol.find()) {
            return true;
        }else {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("회원가입");

        mEditId = (EditText) findViewById(R.id.signup_id_input);
        mEditPw = (EditText) findViewById(R.id.signup_pw_input);
        mEditName = (EditText) findViewById(R.id.signup_name_input);
        mEditPhone = (EditText) findViewById(R.id.signup_phone_input);
        mEditAddress = (EditText) findViewById(R.id.signup_address_input);
        mAgreeDecline = (RadioButton) findViewById(R.id.signup_agree_decline);
        mAgreeAccept = (RadioButton) findViewById(R.id.signup_agree_accept);

        SharedPreferences prefs = getSharedPreferences("person_info", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String prefsId = prefs.getString("id", "");
        mDupButton = (Button) findViewById(R.id.signup_id_isDup);
        mDupButton.setOnClickListener(v -> {
            if (mEditId.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                isIdDup = false;
            } else if(prefsId.equals(mEditId.getText().toString())) {
                Toast.makeText(getApplicationContext(), "이미 사용 중인 아이디입니다.", Toast.LENGTH_SHORT).show();
                isIdDup = false;
            } else {
                Toast.makeText(getApplicationContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                isIdDup = true;
            }
        });

        //완료 버튼
        signup_commit = (Button) findViewById(R.id.signup_commit);
        //완료 버튼 클릭시, 로그인 페이지로 이동
        signup_commit.setOnClickListener(v -> {
            if (isIdDup) {
                if (mEditPw.getText().toString().length() >= 4 && mEditPw.getText().toString().length() < 9) {
                    if (check_validation(mEditPw.getText().toString())) {
                        if (mAgreeAccept.isChecked()) {
                            editor.putString("id", mEditId.getText().toString());
                            editor.putString("pw", mEditPw.getText().toString());
                            editor.putString("name", mEditName.getText().toString());
                            editor.putString("phone", mEditPhone.getText().toString());
                            editor.putString("address", mEditAddress.getText().toString());
                            editor.putBoolean("isAgree",true);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "수집 동의하셔야 회원가입 가능합니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "유효하지 않은 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호는 4~8글자만 가능합니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "아이디 변경한 뒤 중복 확인 후 다시 시도하세요", Toast.LENGTH_SHORT).show();
            }
        });
    }
};