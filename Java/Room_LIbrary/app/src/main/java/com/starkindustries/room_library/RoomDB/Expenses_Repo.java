package com.starkindustries.room_library.RoomDB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.starkindustries.room_library.Expenses;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Expenses_Repo
{
    public static ExecutorService dbwriteexecutor;
    public DatabaseHandler db;
    public static ExpensesDAO expensesdao;
    public static LiveData<ArrayList<Expenses>> get_expenses;
    public Expenses_Repo(Application application)
    {
        db = DatabaseHandler.getDB(application);
        expensesdao= db.expensesDAO();
    }
    public void insert_Expense(Expenses expenses)
    {
        dbwriteexecutor = Executors.newFixedThreadPool(4);

    }

}
