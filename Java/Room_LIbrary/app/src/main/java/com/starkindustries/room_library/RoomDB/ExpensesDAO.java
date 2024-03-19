package com.starkindustries.room_library.RoomDB;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import com.starkindustries.room_library.Expenses;
import java.util.ArrayList;
@Dao
public interface ExpensesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_expense(Expenses expense);

    @Update
    public void update_expense(Expenses expense);
    @Delete
    public void delete_expense(Expenses expenses);
}
