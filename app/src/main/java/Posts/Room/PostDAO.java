package Posts.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PostDAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(PostRoom pPost);

    @Update
    public void update(PostRoom pPost);

    @Delete
    public void delete(PostRoom pPost);

    @Query( "SELECT * FROM Post")
    public List<PostRoom> getPosts();

}
