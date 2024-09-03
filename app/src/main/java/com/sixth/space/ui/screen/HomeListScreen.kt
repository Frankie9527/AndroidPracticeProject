package com.sixth.space.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.model.RemoteViewModel

/**
 * @author: Frankie
 * @Date: 2024/7/11
 * @Description:
 *
 */

@Composable
fun HomeListScreen(page: Int, viewModel: RemoteViewModel = hiltViewModel(key = page.toString())) {
    LaunchedEffect(true) { // Restart the effect when the pulse rate changes
        viewModel.fetchHomeState(page)
    }
    val viewState = viewModel.homeState.collectAsStateWithLifecycle()
    viewState.value?.data?.let {
        HomeItemPager(it)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeItemPager(
    videos: List<VideoInfo>
) {
    val count = rememberPagerState(pageCount = {
        videos.size
    })
    VerticalPager(state = count) { verticalPage ->
        // Our page content
       HomeItemView(videos[verticalPage])
    }
}

@Composable
fun HomeItemView(video:VideoInfo){
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = video.title)
    }
}