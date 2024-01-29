package com.mmreda.mohamedredatask.domain.usecases.home

import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto
import com.mmreda.mohamedredatask.domain.repo.home.HomeRepo
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor (
    private val repo: HomeRepo
) {

    suspend operator fun invoke(): List<PostDto> {
        return repo.getAllPosts()
    }
}