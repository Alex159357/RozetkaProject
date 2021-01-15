package com.bmby.rozetkatestproject.framework.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images")
class ImagesCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "width")
    var width: Int,

    @ColumnInfo(name = "height")
    var height: Int,

    @ColumnInfo(name = "color")
    var color: String,

    @ColumnInfo(name = "likes")
    var likes: Int,

    @ColumnInfo(name = "description")
    var description: String,
)