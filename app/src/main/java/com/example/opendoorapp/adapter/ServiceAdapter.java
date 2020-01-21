package com.example.opendoorapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.opendoorapp.R;
import com.example.opendoorapp.model.Service;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter<Service> {

    Context con;
    public ServiceAdapter(Context context, ArrayList<Service> serviceArrayList) {
        super(context,0, serviceArrayList);
        con = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    private View initView(int position, View convertView, ViewGroup parent) {
        /*if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_spinner, parent, false
            );
        }


        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        Service currentItem = getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getName());
        }

        return convertView;*/
        TextView viewText = new TextView(con);

        viewText.setText(getItem(position).getName());
        viewText.setTextSize(30);
        viewText.setPadding(5, 7, 5, 7);

        // sets the first variable in the middle
        if (position == 0)
            viewText .setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        //puts a yellow background in the IM CRISIS option
        if (position == 1 && getItem(position).getName().equals("I AM IN CRISIS")) {
            viewText .setBackgroundColor(Color.YELLOW);
        }

        return viewText ;
    }

}
