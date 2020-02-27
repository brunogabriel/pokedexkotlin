package dependencies

/**
 * Created by bruno on 27/02/20
 */
object Modules {
    const val DOMAIN = ":domain"

    object CORE {
        const val STYLEGUIDE = ":core:style-guide"
        const val APP = ":core:core-app"
        const val TEST = ":core:core-test"
    }

    object DATA {
        const val REMOTE = ":data:data-remote"
        const val LOCAL = ":data:data-local"
    }

    object FEATURES {
        const val POKEMON_LIST = ":features:pokemon-list"
    }
}

