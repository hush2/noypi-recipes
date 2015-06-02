package com.example.android.noypirecipes;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class RecipeItemActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_pager);

        int id = getIntent().getExtras().getInt("RECIPE_ID");
        String table = getIntent().getExtras().getString("RECIPE_TABLE");

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new RecipePagerAdapter(getSupportFragmentManager(), this, id, table));
        viewPager.setCurrentItem(id);

    }

}
