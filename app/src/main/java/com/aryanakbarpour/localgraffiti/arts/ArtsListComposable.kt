package com.aryanakbarpour.localgraffiti.arts

import android.util.DisplayMetrics
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aryanakbarpour.localgraffiti.data.Art

@ExperimentalFoundationApi
@Composable
fun LocalArtsGrid(
    artsList: List<Art>,
    cols: Int,
    navigateToArtDetail: (Art) -> Unit
) {
    LazyVerticalGrid(
        modifier= Modifier.fillMaxWidth(),
        cells = GridCells.Fixed(count = cols),
        contentPadding = PaddingValues(8.dp)) 
    { 
        items(artsList.size) { i ->
            ArtItem(art = artsList[i], modifier = Modifier.clickable { navigateToArtDetail(artsList[i]) })
        }
    }
}

@Composable
fun ArtItem(art: Art, modifier: Modifier){
    Card(modifier = modifier.padding(4.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(art.pictLink),
                contentDescription = "Art by ${art.writersNames} image",
                modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}