package com.mmreda.mohamedredatask.presentation.ui.main.home

import android.app.Application
import com.developnetwork.universe.base.BaseViewModel
import com.mmreda.mohamedredatask.domain.usecases.home.GetAllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    application: Application
) : BaseViewModel(application) {

    fun getAllPostsUseCase() = handleFlowResponse {
        getAllPostsUseCase.invoke()
    }
}