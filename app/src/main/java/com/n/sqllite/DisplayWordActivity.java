package com.n.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DisplayWordActivity extends AppCompatActivity {

    private ListView lstWords;
    private Button btnSearch;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        lstWords = findViewById(R.id.lstWords);
        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etWord);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetWordByName();
            }
        });

        LoadWord();

    }

    private List<Word> GetWordByName(String word, SQLiteDatabase db) {
        List<Word> dictionaryList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tblWord = '"+word+"'", null );

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                dictionaryList.add(new Word(cursor.getInt(0), cursor.getString(1),cursor.getString(2)));
            }

        }
        return dictionaryList;
    }

    private void LoadWord(){
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList;
        wordList = myHelper.GetAllWords(sqLiteDatabase);

        HashMap<String,String> hashMap = new HashMap<>();

        for (int i =0; i <wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(),wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>(hashMap.keySet()));

                lstWords.setAdapter(stringArrayAdapter);
    }
}
