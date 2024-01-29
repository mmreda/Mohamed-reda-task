package com.mmreda.mohamedredatask.data.repo.home

import com.mmreda.mohamedredatask.data.remote.ApiService
import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto
import com.mmreda.mohamedredatask.domain.repo.home.HomeRepo

class HomeRepoImpl(private val apiService: ApiService) : HomeRepo {

    override suspend fun getAllPosts(): List<PostDto> {
        return apiService.getAllPosts()
    }

}