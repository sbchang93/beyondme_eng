package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

public class ListView02CustomActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    ListView mListview;
    String[] titles = {
            "Avengers (2019)",
            "Iron man (2015)",
            "Ant man (2016)",
            "Dragon Warrior (2017)",
            "Game of Throns (2019)",
            "Star (2013)",
            "Dream (2010)",
            "Contact (2004)",
            "Bat man (2015)",
            "Super man (2015)"
    };
    Integer[] images = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.a,
            R.drawable.b
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view02_custom);
        CustomList adapter = new CustomList(ListView02CustomActivity.this);
        mListview=(ListView)findViewById(R.id.listview_listview02);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), titles[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        private CustomList(Activity context) {
            super(context, R.layout.listitem02, titles);
            this.context = context;
        }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem02, null, true);
            ImageView imageview = (ImageView) rowView.findViewById(R.id.image_listview02);
            TextView title = (TextView) rowView.findViewById(R.id.title_listview02);
            TextView rating = (TextView) rowView.findViewById(R.id.rating_listview02);
            TextView genre = (TextView) rowView.findViewById(R.id.genre_listview02);
            TextView year = (TextView) rowView.findViewById(R.id.year_listview02);

            title.setText(titles[position]);
            imageview.setImageResource(images[position]);
            rating.setText("9.0"+position);
            genre.setText("Action Movie");
            year.setText(2010+position+"");
            return rowView;
        }


    }
}
