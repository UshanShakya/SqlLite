package com.n.sqllite;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.view.View;
import android.widget.Button;
        import android.widget.EditText;
import android.widget.Toast;

import helper.MyHelper;

public class MainActivity extends AppCompatActivity {
    EditText etWord,etMeaning;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAdd = findViewById(R.id.btnAdd);

        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = myHelper.InsertData(etWord.getText().toString(),etMeaning.getText().toString(),sqLiteDatabase);
                if (id > 0)
                {
                    Toast.makeText(MainActivity.this,"Successful " +id,Toast.LENGTH_LONG).show();
                    etWord.setText("");
                    etMeaning.setText("");

                }
                else
                    {
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                        etWord.setText("");
                        etMeaning.setText("");
                        etWord.hasFocus();



                    }
            }
        });
    }
}
