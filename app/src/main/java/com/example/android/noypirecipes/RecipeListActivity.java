package com.example.android.noypirecipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt(MainActivity.EXTRA_CATEGORY);

        DatabaseHelper db = DatabaseHelper.getInstance(this);

        Category category = db.getCategory(id);

        setTitle(category.getTitle());

        ListView listView = (ListView) findViewById(R.id.listView);

        String table = category.getTable();

        ArrayList<Recipe> recipes = db.getAllRecipes(table);

        listView.setAdapter(new RecipeAdapter(this, table, recipes));

    }


}
