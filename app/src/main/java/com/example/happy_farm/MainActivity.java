package com.example.happy_farm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

//不要忘记实现接口

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private EditText editText_user, editText_password;/*获取两个输入框id*/
    private MyDatabaseHelper dbHelper;/*这个元素暂时没有用到，没有需要创建数据库的地方*/
    private String inputText_user, inputText_password;/*用户输入的账号密码*/
    private int flag = 0;/*判断用户输入的账号密码是否和输入库中一致，一致则flag的值变为1*/

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.login);/*登录键*/
        Button register = (Button) findViewById(R.id.register);/*注册键*/
        editText_user = (EditText) findViewById(R.id.user);
        editText_password = (EditText) findViewById(R.id.password);
        editText_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //dbHelper = new MyDatabaseHelper(this, "login2.db", null, 1);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.login:
                SQLiteDatabase stuDb = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "/login.db", null);
                File a;
                a = getFilesDir();

                String trim = editText_user.getText().toString().trim();
                System.out.println(trim);
                inputText_user = editText_user.getText().toString();
                inputText_password = editText_password.getText().toString();
                Cursor cursor = stuDb.rawQuery("SELECT * FROM login WHERE user=? AND password=?", new String[]{inputText_user, inputText_password});
                System.out.println(cursor.moveToFirst());
                if (cursor.moveToFirst()) {
                    flag = 1;
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, the_root_page_activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "账号或密码不存在！", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, inputText_user + "!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register:
                Intent intent = new Intent(MainActivity.this, registerPage.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

}
