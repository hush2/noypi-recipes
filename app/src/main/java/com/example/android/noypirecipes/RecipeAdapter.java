package com.example.android.noypirecipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    Typeface font;
    String table;

    public RecipeAdapter(Context context, String table, ArrayList<Recipe> objects) {
        super(context, 0, objects);
        this.table = table;
        //font = Typeface.createFromAsset(context.getAssets(), "fonts/CaviarDreams.ttf");
    }

    static class ViewHolder {

        public int id;
        public TextView recipeTitle;
        public ImageView recipeImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_row, parent, false);
            TextView recipeTitle = (TextView) convertView.findViewById(R.id.recipeTitle);
            ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipeImage);
            //recipeTitle.setTypeface(font);
            viewHolder = new ViewHolder();
            viewHolder.recipeTitle = recipeTitle;
            viewHolder.recipeImage = recipeImage;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.id = recipe.getId();
        viewHolder.recipeTitle.setText(recipe.getTitle());
        try {
            InputStream is = getContext().getAssets().open(recipe.getImage());
            Drawable image = Drawable.createFromStream(is, null);
            viewHolder.recipeImage.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                Intent intent = new Intent(getContext(), RecipeItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("RECIPE_ID", viewHolder.id);
                bundle.putString("RECIPE_TABLE", table);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
