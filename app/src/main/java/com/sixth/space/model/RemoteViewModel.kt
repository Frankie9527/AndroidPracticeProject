package com.sixth.space.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.data.dao.VideoInfo

import com.sixth.space.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

import javax.inject.Inject

/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 * private val dataRepositoryRepository: DataRepositorySource
 *
 */
@HiltViewModel
class RemoteViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) :
    ViewModel() {

    val homeDailyState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchTiktokData("","")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )
    val homeRecommendState: StateFlow<Resource<List<VideoInfo>>?> = dataRepositoryRepository
        .fetchTiktokData("","")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

}