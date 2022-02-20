package com.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.data.model.local.AuthorsEntity

@Database(entities = [AuthorsEntity::class], version = 2, exportSchema = false)
abstract class AuthorDataBase : RoomDatabase() {
    abstract fun getAuthorDao(): AuthorDAO

}