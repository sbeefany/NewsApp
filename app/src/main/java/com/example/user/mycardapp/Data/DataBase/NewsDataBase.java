package com.example.user.mycardapp.Data.DataBase;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {DBModel.class}, version = 1)
public abstract class NewsDataBase extends RoomDatabase {
    private static NewsDataBase instance;
    private static final String DATABASE_NAME = "NewsRoomBd.db";

    public abstract NewsDao filmDao();

    public synchronized static NewsDataBase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDataBase.class,
                    DATABASE_NAME)
                    .build();
        }
        return instance;
    }

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
