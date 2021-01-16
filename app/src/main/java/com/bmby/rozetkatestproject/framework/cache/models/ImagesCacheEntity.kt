package com.bmby.rozetkatestproject.framework.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bmby.rozetkatestproject.logic.domain.models.ImageUrlsModel


@Entity(tableName = "images")
class ImagesCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

)