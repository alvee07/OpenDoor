/**
 * AUCSC 320
 * Arnold Gihozo
 *
 * Class helps to change the background of a certain item within the spinner drop
 * down menu.
 *
 * The code for this class has been found at the source bellow
 * https://stackoverflow.com/questions/37217962/change-spinner-background-color-for-specific-item
 *
 * Accessed on January 18th 2020
 *
 * Modified version:
 *   - Modified the textsized
 *   - Modified the padding
 *   - moddifed the variable names
 *   - Modified the displayed color
 */

package com.example.opendoorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    List<String> strings;
    Context context;

    public SpinnerAdapter(List<String> stringList, Context context) {
        strings = stringList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public String getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView viewText = new TextView(context);

        viewText .setText(strings.get(position));
        viewText .setTextSize(30);
        viewText .setPadding(5, 7, 5, 7);
        
        // sets the first variable in the middle
        if (position == 0)
            viewText .setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        //here you can use position or string
        if (position == 1 && strings.get(position).equals("I AM IN CRISIS")) {
            viewText .setBackgroundColor(Color.YELLOW);
        }

        return viewText ;
    }

}
