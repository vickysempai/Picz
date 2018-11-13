package com.picz.usuario.picz_poo;

import android.app.ListActivity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import Posts.Room.PostDataBase;
import Posts.Room.PostRoom;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    public PostDataBase db;
   // private View pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = db.getAppDatabase(getApplicationContext());

        Log.w("mitag", "problema for linea 35");
        try {
            final List<PostRoom> posts = db.PostDAO().getPosts();
            ListAdapter adapter = new ListAdapter(this, posts);


            //for (PostRoom post : posts) {
            // Attach the adapter to a ListView
                ListView listView = (ListView) findViewById(R.id.ListaPosts);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent PostEspecifico = new Intent(getApplicationContext(), PostEspecifico.class);
                    PostEspecifico.putExtra("comment", posts.get(position).getComment());
                    PostEspecifico.putExtra("date", posts.get(position).getDate());
                    PostEspecifico.putExtra("image", posts.get(position).getPhoto());
                    startActivity(PostEspecifico);
                }
            });

            listView.setAdapter(adapter);
               /* pantalla =findViewById(R.id.pantalla);
                pantalla.add*/
            //}
        }catch(Exception e){
            //pass
            Log.w("mitag", "problema for linea 61");
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        FloatingActionButton gallery = (FloatingActionButton) findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED){
            return;
        }
        if (requestCode == PICK_IMAGE ){
            Uri selectedImage = data.getData();

            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filepath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(filepath);

            Intent cameraActivity = new Intent(getApplicationContext(), CameraActivity.class);
            cameraActivity.putExtra("Photo", bitmap);
            startActivity(cameraActivity);

        }
        else{
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");

            Intent cameraActivity = new Intent(getApplicationContext(), CameraActivity.class);
            cameraActivity.putExtra("Photo", bitmap);
            startActivity(cameraActivity);
        }
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }


}
