package com.aryanakbarpour.localgraffiti.data.remote

import com.aryanakbarpour.localgraffiti.data.Art

data class StreetArtApiResponse (
    val streetArts: List<Art> = emptyList()) {
}