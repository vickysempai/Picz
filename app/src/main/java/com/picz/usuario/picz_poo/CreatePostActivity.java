package com.picz.usuario.picz_poo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Posts.Room.PostDataBase;
import Posts.Room.PostRoom;

import static android.Manifest.permission.READ_CONTACTS;


public class CreatePostActivity extends AppCompatActivity  {

    // UI references.
    private AutoCompleteTextView mEmailView;
    ImageView imageView;

    public PostDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.TextComment);

        imageView = (ImageView)findViewById(R.id.imageView);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            Bitmap photo = parametros.getParcelable("Photo");
            imageView.setImageBitmap(photo);
        }

        Button mEmailSignInButton = (Button) findViewById(R.id.btnComment);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                db = db.getAppDatabase(getApplicationContext());
                Drawable drawable =  imageView.getDrawable();
                BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
                Bitmap photo = bitmapDrawable .getBitmap();//Es la foto
                String Comment = mEmailView.getText().toString();//Es el comentario

                Date currentTime = Calendar.getInstance().getTime();//Es la fecha, no se como la da

                PostRoom post = new PostRoom();
                post.setComment(Comment);
                post.setPhoto(photo);
                post.setDate(currentTime.toString());
                db.PostDAO().insert(post);
                Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity);
                //Es la fecha, no se como la da
                //Aqui se crearia el post y se guardaria en la base de datos
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


}

