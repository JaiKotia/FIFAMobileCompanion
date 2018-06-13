package com.mrkotia.fifamobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        String OVR="0", tags;
        String cardType="unknown";
        if (cursor != null && cursor.moveToFirst()) do {
            //result = cursor.getString(cursor.getColumnIndex("position"));
            //dbSearchResults.add(cursor.getString(cursor.getColumnIndex("fullname")));

            tags = cursor.getString(cursor.getColumnIndex("tags"));
            List<String> stringList = Arrays.asList(tags.split(","));
            for (int i=0; i<stringList.size(); i++) {
                if(stringList.get(i).contains("BASEOVR_")){
                    String[] parts = stringList.get(i).split("_");
                    OVR = parts[1];
                }
                if(stringList.get(i).contains("BASEITEM")){
                    cardType="Base";
                }
                if(stringList.get(i).contains("TOTY")){
                    cardType="TOTY";
                }
                if(stringList.get(i).contains("TOTS")){
                    cardType="TOTS";
                }
                if(stringList.get(i).contains("GOLDEN_TICKET")){
                    cardType="Golden Ticket";
                }
                if(stringList.get(i).contains("MATCHUPS_PLAYER")){
                    cardType="Matchups";
                }
                if(stringList.get(i).contains("VSA")){
                    cardType="VSA";
                }
                if(stringList.get(i).contains("EUROSTARS")){
                    cardType="EURO Star";
                }
                if(stringList.get(i).contains("POTM")){
                    cardType="POTM";
                }
                if(stringList.get(i).contains("ICONS")){
                    cardType="Icon";
                }
                if(stringList.get(i).contains("TOTW")){
                    cardType="TOTW";
                }
            }



            playerSearchObject = new PlayerSearchObject(cursor.getString(cursor.getColumnIndex("fullname")),
                                                        cursor.getString(cursor.getColumnIndex("position")),
                                                        cardType,
                                                        OVR,
                                                        cursor.getString(cursor.getColumnIndex("playerId")),
                                                        cursor.getString(cursor.getColumnIndex("id")));
            playerSearchResults.add(playerSearchObject);
        } while (cursor.moveToNext());
        cursor.close();
        Collections.sort(playerSearchResults, new Comparator<PlayerSearchObject>() {
            @Override
            public int compare(PlayerSearchObject o1, PlayerSearchObject o2){
                return Integer.valueOf(o2.getBaseOVR()).compareTo(Integer.valueOf(o1.getBaseOVR()));
            }
        });
        return playerSearchResults;
    }

}