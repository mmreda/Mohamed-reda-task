package com.mmreda.mohamedredatask.domain.repo.home

import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto

interface HomeRepo {

    suspend fun getAllPosts(): List<PostDto>
}