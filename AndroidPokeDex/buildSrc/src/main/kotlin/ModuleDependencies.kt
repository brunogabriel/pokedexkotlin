object ModuleDependencies {
    const val network = ":network"
    const val shared = ":shared"
    const val styleguide = ":styleguide"
    const val database = ":database"
    const val pokemonList = ":pokemonlist"

    val modules = listOf(network, shared, styleguide, database, pokemonList)
}