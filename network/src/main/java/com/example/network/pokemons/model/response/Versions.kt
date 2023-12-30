package com.example.network.pokemons.model.response

import com.squareup.moshi.Json

data class Versions(
    @Json(name = "generation-i")
    val generationI: GenerationI,
    @Json(name = "generation-ii")
    val generationII: GenerationIi,
    @Json(name = "generation-iii")
    val generationIII: GenerationIii,
    @Json(name = "generation-iv")
    val generationIv: GenerationIv,
    @Json(name = "generation-v")
    val generationV: GenerationV,
    @Json(name = "generation-vi")
    val generationVi: GenerationVi,
    @Json(name = "generation-vii")
    val generationVii: GenerationVii,
    @Json(name = "generation-viii")
    val generationViii: GenerationViii
)