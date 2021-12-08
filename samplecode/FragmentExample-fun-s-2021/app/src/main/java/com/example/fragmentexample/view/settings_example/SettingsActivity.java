package com.example.fragmentexample.view.settings_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.utils.PreferenceUtils;

import static com.example.fragmentexample.constants.Constants.emailKey;
import static com.example.fragmentexample.constants.Constants.memoKey;
import static com.example.fragmentexample.constants.Constants.spNameKey;
import static com.example.fragmentexample.constants.Constants.phoneKey;
import static com.example.fragmentexample.constants.Constants.updateCardKey;

// Reference URLs
// https://kumgo1d.tistory.com/31

public class SettingsActivity extends AppCompatActivity {

    private static final int SHOW_SETTINGS_PREFERENCE = 1;

    TextView mtvName;
    TextView mtvPhone;
    TextView mtvEmail;
    TextView mtvMemo;
    TextView mtvUpdateKey;

    TextView tvText1, tvText2;
    SearchView searchView1;

    ListView list1;
    String[] data_list = {"aaaa", "bbbb", "cccc", "aabb", "ccdd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tvText1 = findViewById(R.id.textView1);
        tvText2 = findViewById(R.id.textView2);

        list1 = findViewById(R.id.list1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String> (
                this, android.R.layout.simple_expandable_list_item_1, data_list);
        list1.setAdapter(adapter1);
        list1.setTextFilterEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean bChecked = PreferenceUtils.getInstance().getBoolean(this, spNameKey);
        mtvName = findViewById(R.id.textview_name);
        mtvName.setVisibility(bChecked ? View.VISIBLE : View.GONE);

        bChecked = PreferenceUtils.getInstance().getBoolean(this, phoneKey);
        mtvPhone = findViewById(R.id.textview_phone);
        mtvPhone.setVisibility(bChecked ? View.VISIBLE : View.GONE);

        bChecked = PreferenceUtils.getInstance().getBoolean(this, emailKey);
        mtvEmail = findViewById(R.id.textview_email);
        mtvEmail.setVisibility(bChecked ? View.VISIBLE : View.GONE);

        bChecked = PreferenceUtils.getInstance().getBoolean(this, memoKey);
        mtvMemo = findViewById(R.id.textview_memo);
        mtvMemo.setVisibility(bChecked ? View.VISIBLE : View.GONE);

        bChecked = PreferenceUtils.getInstance().getBoolean(this, updateCardKey);
        mtvUpdateKey = findViewById(R.id.textview_update_card);
        mtvUpdateKey.setVisibility(bChecked ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Query Menu Item 5
        MenuItem searchItem = menu.findItem(R.id.item5);
        searchView1 = (SearchView)searchItem.getActionView();
        searchView1.setQueryHint(new String("Enter what you want to search!"));
        QueryTextListener4SearchView listener = new QueryTextListener4SearchView();
        searchView1.setOnQueryTextListener(listener);

        ActionExpandListener4SearchView listener2 = new ActionExpandListener4SearchView();
        searchItem.setOnActionExpandListener(listener2);

        // Settings Menu
        menu.add("Settings");
        return true;

//        super.onCreateOptionsMenu(menu);
//        menu.add("Settings");
//        return true;
    }

    class QueryTextListener4SearchView implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            tvText2.setText(query);
            searchView1.clearFocus();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            tvText1.setText(newText);

            list1.setFilterText(newText);
            if(newText.length() == 0) {
                list1.clearTextFilter();
            }
            return true;
        }
    }


// ###   Simple Reference Code for SearchView.OnQueryTextListener
//    class QueryTextListener4SearchView implements SearchView.OnQueryTextListener {
//
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            tvText2.setText(query);
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            tvText1.setText(newText);
//            return true;
//        }
//    }

    class ActionExpandListener4SearchView implements MenuItem.OnActionExpandListener {

        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            tvText1.setText("Expand - It is unfolded");
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            tvText1.setText("Collapse - It is folded");
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case 0:
                Intent intent = new Intent(this, SettingsPreferenceActivity.class);
                startActivityForResult(intent, SHOW_SETTINGS_PREFERENCE);

            case R.id.item1 :
                tvText1.setText("Menu 1 is selected.");
                break;
            case R.id.item2 :
                tvText1.setText("Menu 2 is selected.");
                break;
            case R.id.item3 :
                tvText1.setText("Menu 3 is selected.");
                break;
            case R.id.item4 :
                tvText1.setText("Menu 4 is selected.");
                break;
        }
        return false;
    }


}