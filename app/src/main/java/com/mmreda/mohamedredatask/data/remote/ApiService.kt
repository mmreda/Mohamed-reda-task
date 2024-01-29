package com.mmreda.mohamedredatask.data.remote

import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto
import retrofit2.http.GET


interface ApiService {

   @GET("posts")
   suspend fun getAllPosts(): List<PostDto>
}