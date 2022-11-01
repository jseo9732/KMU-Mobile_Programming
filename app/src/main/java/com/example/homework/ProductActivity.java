package com.example.homework;

import static com.example.homework.SignupActivity.SEP;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ListView list;
    String mTitle[] = {"농심 신라면 120g", "농심 신라면 건면 97g", "오뚜기 진라면 순한맛 120g", "오뚜기 참깨라면 115g", "오뚜기 김치라면 120g"};
    String mPrice[] = {"1100원", "1300원", "900원", "1500원", "1200원"};
    int mImages[]={R.drawable.sin,R.drawable.singun,R.drawable.ginsun,R.drawable.sam,R.drawable.kimchi};
    private Switch mEditSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setTitle("상품 정보");

        list = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(this, mTitle, mPrice, mImages);
        list.setAdapter(adapter);

        SharedPreferences login_prefs = getSharedPreferences("login_info", Activity.MODE_PRIVATE);
        Boolean isLogin = login_prefs.getBoolean("isLogin", false);

        SharedPreferences prefs = getSharedPreferences("person_info", Activity.MODE_PRIVATE);

        mEditSwitch = (Switch) findViewById(R.id.switch1);
        mEditSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    if(isLogin) {
                        String userId = login_prefs.getString("loginId", "");
                        String logUserUser = prefs.getString(userId, "");
                        String[] saveData = logUserUser.split(SEP);
                        AlertDialog.Builder builderUser = new AlertDialog.Builder(ProductActivity.this);
                        builderUser.setTitle("회원 정보 조회");
                        builderUser.setMessage("ID: " + saveData[0] + "\n" + "이름: " + saveData[2] + "\n" + "전화번호: " + saveData[3] + "\n" + "주소: " + saveData[4]);
                        builderUser.setPositiveButton("확인", null);
                        builderUser.create().show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                        builder.setTitle("비로그인 상태입니다.");
                        builder.setMessage("회원가입하시겠습니까?");
                        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.create().show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "체크 해제", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class ListAdapter extends ArrayAdapter<String>{
        Context context;
        String rTitle[];
        String rPrice[];
        int rImgs[];

        ListAdapter(Context c, String title[],String price[],int imgs[]){
            super(c,R.layout.list_view_row,R.id.textView1,title);
            this.context=c;
            this.rTitle=title;
            this.rPrice=price;
            this.rImgs=imgs;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View list_view_row = layoutInflater.inflate(R.layout.list_view_row,parent,false);

            ImageView images = list_view_row.findViewById(R.id.image);
            TextView myTitle = list_view_row.findViewById(R.id.textView1);
            TextView myDescription = list_view_row.findViewById(R.id.textView2);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rPrice[position]);

            return list_view_row;
        }
    }
}