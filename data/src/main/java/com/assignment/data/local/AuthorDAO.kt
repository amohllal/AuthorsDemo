package com.assignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.data.model.local.AuthorsEntity
import io.reactivex.Single

@Dao
interface AuthorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveListOfAuthors(list: List<AuthorsEntity>)


    @Query("SELECT * FROM Authors")
    fun getAuthorsFromDatabase(): Single<List<AuthorsEntity>>
}