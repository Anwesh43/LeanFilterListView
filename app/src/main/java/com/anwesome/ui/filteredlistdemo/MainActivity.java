package com.anwesome.ui.filteredlistdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.leanfilterlist.FilteredListLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile);
        FilteredListLayout filteredListLayout = new FilteredListLayout(this);
        filteredListLayout.addFilterButton("Sports");
        filteredListLayout.addFilterButton("Politics");
        filteredListLayout.addFilterButton("Science");
        filteredListLayout.addFilterButton("Books");
        filteredListLayout.addFilterButton("Education");
        filteredListLayout.addFilterButton("Tech");
        filteredListLayout.addListComponent(bitmap,"Arsene Wenger","Manager of Arsenal",new ArrayList<String>(){{
            add("Sports");
        }});
        filteredListLayout.addListComponent(bitmap,"Stephen Hawking","Scientist who breaks all barriers",new ArrayList<String>(){{
            add("Science");
            add("Books");
            add("Education");
        }});
        filteredListLayout.addListComponent(bitmap,"Elon Musk","Visionary who dosen't know any limit",new ArrayList<String>(){{
            add("Tech");
        }});
        filteredListLayout.addListComponent(bitmap,"Real Madrid","Sports team",new ArrayList<String>(){{
            add("Sports");
        }});
        filteredListLayout.addListComponent(bitmap,"Donald Trump","President of United States of America",new ArrayList<String>(){{
            add("Politics");
        }});
        setContentView(filteredListLayout);
    }
}
