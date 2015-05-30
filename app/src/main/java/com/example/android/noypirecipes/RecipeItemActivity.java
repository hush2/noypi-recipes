package com.example.android.noypirecipes;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;
import java.io.InputStream;


public class RecipeItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_pager);

        int id = getIntent().getExtras().getInt("RECIPE_ID");
        String table = getIntent().getExtras().getString("RECIPE_TABLE");
//
//        TextView textView2 = (TextView) findViewById(R.id.textView2);
//        HtmlTextView recipeText = (HtmlTextView) findViewById(R.id.recipeText);
//
//        DatabaseHelper db = DatabaseHelper.getInstance(this);
//        Recipe recipe = db.getRecipe(table, id);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new RecipePagerAdapter(getSupportFragmentManager(), this, id, table));
        viewPager.setCurrentItem(id);

//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
//        recipeText.setTypeface(font);
//        textView2.setTypeface(font);

//        textView2.setText(recipe.getTitle());
//
//        recipeText.setHtmlFromString(recipe.getText(), false);
//
//        ImageView imageView = (ImageView) findViewById(R.id.recipeImage2);
//        try {
//            InputStream is = getAssets().open(recipe.getImage());
//            Drawable image = Drawable.createFromStream(is, null);
//            imageView.setImageDrawable(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

}
