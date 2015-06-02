package com.example.android.noypirecipes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;
import java.io.InputStream;

public class RecipeFragment extends Fragment {

    private int id;
    private String table;
    private Context context;

    public RecipeFragment() {
    }

    public RecipeFragment(Context context, int id, String table) {

        this.id = id;
        this.table = table;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = (View) inflater.inflate(R.layout.recipe_fragment, container, false);

        TextView recipeTitle = (TextView) rootView.findViewById(R.id.recipeTitle);
        HtmlTextView recipeText = (HtmlTextView) rootView.findViewById(R.id.recipeText);
        ImageView recipeImage = (ImageView) rootView.findViewById(R.id.recipeImage);

        DatabaseHelper db = DatabaseHelper.getInstance(context);
        Recipe recipe = db.getRecipe(table, id);

        recipeTitle.setText(recipe.getTitle());
        recipeText.setHtmlFromString(recipe.getText(), false);
        try {
            InputStream is = context.getAssets().open(recipe.getImage());
            Drawable image = Drawable.createFromStream(is, null);
            recipeImage.setImageDrawable(image);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.d("TAG", "Can't find image " + recipeImage);
        }


        return rootView;
    }
}
