interface IClickPokemonListener {
    fun onClick(pokemonItem: PokemonModel)
}

data class PokemonModel(
    val name: String,
    val number: Int,
    val url: String)