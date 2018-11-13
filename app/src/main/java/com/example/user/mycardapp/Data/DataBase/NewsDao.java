package com.example.user.mycardapp.Data.DataBase;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news WHERE category = :category")
    Single<List<DBModel>> getAllNews (String category);

    @Query("SELECT * FROM news WHERE id = :id")
    DBModel getNewsById (int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll (DBModel... newsEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (DBModel newsEntity);

    @Query("DELETE FROM news WHERE category = :category")
    void deleteAllFromOneCategory (String category);

    @Delete
    void delete (DBModel dbModel);

}
