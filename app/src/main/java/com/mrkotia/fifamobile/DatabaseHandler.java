package com.mrkotia.fifamobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by jai on 11/6/18.
 */

public class DatabaseHandler extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "fm_players.db";
    private static final int DATABASE_VERSION = 1;
    public static ArrayList<String> fullname = new ArrayList<>();

    private ArrayList<String> dbSearchResults = new ArrayList<>();

    private ArrayList<String> dbPositionResults = new ArrayList<>();

    private ArrayList<PlayerSearchObject> playerSearchResults = new ArrayList<>();

    private PlayerSearchObject playerSearchObject;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void initializeAdapter() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("card_table", new String[]{"fullname"}, null, null, null, null, null);
        String fieldToAdd=null;
        if (cursor != null && cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                fieldToAdd = cursor.getString(0);
                fullname.add(fieldToAdd);
            }
        }
        cursor.close();
    }

    public ArrayList<PlayerSearchObject> getPlayerSearchResult(String search) {

        playerSearchResults.clear();
        String query = "SELECT * FROM card_table WHERE fullname LIKE '%" + search + "%'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String result=null;
        if (cursor != null && cursor.moveToFirst()) do {
            //result = cursor.getString(cursor.getColumnIndex("position"));
            //dbSearchResults.add(cursor.getString(cursor.getColumnIndex("fullname")));

            playerSearchObject = new PlayerSearchObject(cursor.getString(cursor.getColumnIndex("fullname")),
                                                        cursor.getString(cursor.getColumnIndex("position")),
                                                        cursor.getString(cursor.getColumnIndex("DRI")),
                                                        cursor.getString(cursor.getColumnIndex("tags")));
            playerSearchResults.add(playerSearchObject);
        } while (cursor.moveToNext());
        cursor.close();
        return playerSearchResults;
    }

}