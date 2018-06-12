package com.mrkotia.fifamobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jai on 12/6/18.
 */

public class PlayerSearchAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<PlayerSearchObject> objects;

    private class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
    }

    public PlayerSearchAdapter(Context context, ArrayList<PlayerSearchObject> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public PlayerSearchObject getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.player_search_item, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.player_name);
            holder.textView2 = (TextView) convertView.findViewById(R.id.player_position);
            holder.textView3 = (TextView) convertView.findViewById(R.id.player_OVR);
            holder.textView4 = (TextView) convertView.findViewById(R.id.player_tags);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(objects.get(position).getPlayerName());
        holder.textView2.setText(objects.get(position).getPosition());
        holder.textView3.setText(objects.get(position).getBaseOVR());
        holder.textView4.setText(objects.get(position).getTags());

        return convertView;
    }
}
