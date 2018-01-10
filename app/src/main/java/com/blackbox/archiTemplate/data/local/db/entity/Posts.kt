package com.blackbox.archiTemplate.data.local.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by umair on 10/01/2018.
 */

@Entity(tableName = "posts")
data class Posts(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int = 0,

        @ColumnInfo(name = "title")
        val title: String)