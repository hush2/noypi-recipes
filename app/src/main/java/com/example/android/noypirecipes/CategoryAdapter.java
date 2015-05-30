package com.example.android.noypirecipes;

import android.content.Context;
import android.content.Intent;
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

public class CategoryAdapter extends ArrayAdapter<Category> {

    Context context;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, 0, categories);
        this.context = context;
    }

    class ViewHolder {
        public int id;
        public TextView textView;
        public ImageView imageView;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.category_list, parent, false);

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

            viewHolder = new ViewHolder();
            viewHolder.textView = textView;
            viewHolder.imageView = imageView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Category category = getItem(position);

        viewHolder.textView.setText(category.getTitle());
        viewHolder.id = position;

        try {
            InputStream is = getContext().getAssets().open(category.getImage());
            Drawable image = Drawable.createFromStream(is, null);
            viewHolder.imageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecipeListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(MainActivity.EXTRA_CATEGORY, ((ViewHolder) view.getTag()).id);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
