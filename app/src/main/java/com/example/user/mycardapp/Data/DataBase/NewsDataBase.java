package com.example.user.mycardapp.Data.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {DBModel.class}, version = 1)
public abstract class NewsDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "NewsRoomBd.db";
    private static NewsDataBase instance;

    public synchronized static NewsDataBase getInstance (Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext() ,
                    NewsDataBase.class ,
                    DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NewsDao filmDao ();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper (DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker () {
        return null;
    }

    @Override
    public void clearAllTables () {

    }
}
