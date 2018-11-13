package com.picz.usuario.picz_poo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Posts.Room.PostDataBase;

public class PostEspecifico extends AppCompatActivity {

    String comentario,lalola;
    Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Set up the login form.
        ImageView img = (ImageView)findViewById(R.id.imagen);
        TextView com = (TextView)findViewById(R.id.commentario);
        TextView fecha = (TextView)findViewById(R.id.fecha);

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            /*PostEspecifico.putExtra("comment", posts.get(position).getComment());
            PostEspecifico.putExtra("date", posts.get(position).getDate());
            PostEspecifico.putExtra("image", posts.get(position).getPhoto());
            */
            comentario = parametros.getString("comment");
            lalola = parametros.getString("date");
            image = parametros.getParcelable("image");

            img.setImageBitmap(image);
            com.setText(comentario);
            fecha.setText(lalola);
        }


    }
}
