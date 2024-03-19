package com.starkindustries.room_library.RoomDB;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.starkindustries.room_library.Expenses;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = Expenses.class,exportSchema = false,version = 1)
public abstract class DatabaseHandler extends RoomDatabase {
    public abstract ExpensesDAO expensesDAO();
    public static final String DB_NAME="Expenses Database";
    public static final int NO_OF_THREAD=4;
    public static final ExecutorService dbwriteservice = Executors.newFixedThreadPool(NO_OF_THREAD);
    public static volatile DatabaseHandler Instance;
    public static synchronized DatabaseHandler getDB(final Context context)
    {
        if(Instance==null)
        {
            synchronized (DatabaseHandler.class)
            {
                if(Instance==null)
                {
                    Instance= Room.databaseBuilder(context.getApplicationContext(),DatabaseHandler.class,DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return Instance;
    }
    RoomDatabase.Callback roomdatabasecallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbwriteservice.execute(new Runnable() {
                @Override
                public void run() {
                    ExpensesDAO dao = Instance.expensesDAO();
                    dao.update_expense(new Expenses(1,"Sandeep","10000000000"));
                }
            });
        }
    };
    public class Viewmodel extends AndroidViewModel
    {

        public Viewmodel(@NonNull Application application) {
            super(application);
        }
    }

}
