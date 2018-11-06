package com.example.user.mycardapp.Data.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE category = :category")
    Observable<List<DBModel>> getAllNews (String category);

    @Query("SELECT * FROM news WHERE id = :id")
    DBModel getNewsById (int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll (DBModel... newsEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (DBModel newsEntity);

}
