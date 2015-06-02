package com.example.android.noypirecipes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "recipes.db";
    private static int DB_VERSION = 3;

    private String DB_PATH;

    Context context;

    private static DatabaseHelper instance;
    SQLiteDatabase db;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;

        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()) {
            db = getReadableDatabase(); // create an empty database
            copyDatabase();
        } else {
            db = getReadableDatabase();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        copyDatabase();
    }

    private void copyDatabase() {
        try {
            String targetFile = DB_PATH + DB_NAME;
            InputStream src = context.getAssets().open(DB_NAME);
            OutputStream target = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = src.read(buffer)) > 0) {
                target.write(buffer, 0, length);
            }
            target.flush();
            target.close();
            src.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Recipe> getAllRecipes(String table) {

        ArrayList<Recipe> recipes = new ArrayList<>();

        String query = "select * from " + table + " order by title asc";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getInt(0));
                recipe.setTitle(cursor.getString(1));
                recipe.setText(cursor.getString(2));
                recipe.setImage(cursor.getString(3));
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return recipes;
    }

    public Recipe getRecipe(String table, int id) {

        String query = "select title, text, image from " + table + " where _id = " + id;
        Recipe recipe = new Recipe();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            recipe.setTitle(cursor.getString(0));
            recipe.setText(cursor.getString(1));
            recipe.setImage(cursor.getString(2));
        }
        cursor.close();
        return recipe;
    }

    public ArrayList<Category> getAllCategories() {

        ArrayList<Category> categories = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from categories", null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setTable(cursor.getString(1));
                category.setTitle(cursor.getString(2));
                category.setImage(cursor.getString(3));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    public Category getCategory(int id) {

        Category category = new Category();

        Cursor cursor = db.rawQuery("select * from categories where _id = " + id, null);
        if (cursor.moveToFirst()) {
            do {
                category.setId(cursor.getInt(0));
                category.setTable(cursor.getString(1));
                category.setTitle(cursor.getString(2));
                category.setImage(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return category;
    }

    public int getCount(String table) {

        Cursor cursor = db.rawQuery("select count(*) from " + table, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }
        return count;
    }
}
