package com.assignment.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Authors")
data class AuthorsEntity(

    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = null,

    @ColumnInfo(name = "Name")
    val name: String? = null,

    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "UserName")
    val userName: String? = null,

    @ColumnInfo(name = "Email")
    val email: String? = null
)
