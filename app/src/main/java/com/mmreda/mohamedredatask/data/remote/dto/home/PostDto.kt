package com.mmreda.mohamedredatask.data.remote.dto.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDto(
    @SerializedName("userId")
    var userId: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null
) : Parcelable
