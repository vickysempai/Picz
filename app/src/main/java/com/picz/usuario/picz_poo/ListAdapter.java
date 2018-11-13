package com.picz.usuario.picz_poo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Posts.Room.PostRoom;

public class ListAdapter extends ArrayAdapter<PostRoom> {

        public ListAdapter(Context context, List<PostRoom> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            PostRoom post = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
            }
            // Lookup view for data population
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            TextView descripcion = (TextView) convertView.findViewById(R.id.itemName);
            TextView hora = (TextView) convertView.findViewById(R.id.hora);
            // Populate the data into the template view using the data object
            image.setImageBitmap(post.getPhoto());
            descripcion.setText(post.getComment());
            hora.setText(post.getDate());
            // Return the completed view to render on screen
            return convertView;
        }
    }
