package com.mmreda.mohamedredatask.presentation.ui.main.home

import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto

interface OnItemPostClicked {
    fun onItemClicked(postDto: PostDto)
}