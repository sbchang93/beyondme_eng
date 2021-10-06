package com.example.fragmentexample.view.searchview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fragmentexample.R;

public class SearchViewActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private TextView mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        mSearchText = findViewById(R.id.search_text);

        mSearchView = findViewById(R.id.search_view); // SearchView
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 입력받은 문자열 처리
                mSearchText.setText(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 입력란의 문자열이 바뀔 때 처리
                return false;
            }
        });

    }
}