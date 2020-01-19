package com.example.opendoorapp.adapter;

import android.content.Context;
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

    public ServiceAdapter(Context context, ArrayList<Service> serviceArrayList) {
        super(context,0, serviceArrayList);

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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_spinner, parent, false
            );
        }


        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        Service currentItem = getItem(position);

        if (currentItem != null) {
            textViewName.setText(currentItem.getName());
        }

        return convertView;
    }

}
