package Posts.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

@Entity (tableName = "Post")
public class PostRoom {
    @NonNull
    @PrimaryKey
    String Photo;

    String Comment;

    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getPhoto(){
        try {
            byte [] encodeByte=Base64.decode(this.Photo,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void setPhoto(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        this.Photo=temp;
    }

    public PostRoom() {

    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getComment() {
        return Comment;
    }

    public void savePost(PostDataBase db, PostRoom post) {
        db.PostDAO().insert(post);
    }
}
