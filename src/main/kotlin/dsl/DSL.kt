package dsl

abstract class AbstractQuery {
    open var typename: String = ""

    internal val parameters: MutableList<String> = mutableListOf()
    
    // Have the hero query builder take in an alias and episode parameter
    fun hero(alias: String? = null, episode: String? = null, builder: HeroQuery.() -> HeroQuery): AbstractQuery {
        val query = HeroQuery()
        query.builder()
        parameters += query.toString()
        return this
    }

    fun human(alias: String? = null, id: String, builer: HumanQuery.() -> HumanQuery): AbstractQuery {
        val query = HumanQuery()
        query.builer()
        parameters += query.toString()
        return this
    }

    override fun toString(): String {
        return parameters.joinToString(
            separator = "\n",
            prefix = (if (typename == "") "" else typename + " ") + "{" + "\n",
            transform = { parameter ->
                parameter.split("\n")
                    .map { "\t" + it }
                    .joinToString(separator = "\n")
            },
            postfix = "\n" + "}"
        )
    }
}

// Top level query object - stores information about the query written
class Query(override var typename: String = "") : AbstractQuery()

// Top level query builder
fun query(builder: Query.() -> AbstractQuery): Query {
    val query = Query()
    query.builder()
    return query
}

// "Nested" selector - can only be used within the context of the top level query
class HeroQuery : AbstractQuery() {
    override var typename: String = "hero"
    val name: HeroQuery
        get() {
            parameters += "name"
            return this
        }

    fun friends(builder: FriendsQuery.() -> FriendsQuery): HeroQuery {
        val query = FriendsQuery()
        query.builder()
        parameters += query.toString()
        return this
    }
}

class FriendsQuery : AbstractQuery() {
    override var typename: String = "friends"

    val name: FriendsQuery
        get() {
            parameters += "name"
            return this
        }
}

class HumanQuery : AbstractQuery() {
    override var typename: String = "human"

    val name: HumanQuery
        get() {
            parameters += "name"
            return this
        }

    val height: HumanQuery
        get() {
            parameters += "height"
            return this
        }

    fun height(unit: String): HumanQuery {
        parameters += "height" + "    // unit: $unit" // informational purposes
        return this
    }
}


// Ability to add names to previously unnamed queries
fun query(name: String = "", builder: Query.() -> AbstractQuery): Query {
    val query = Query(name)
    query.builder()
    return query
}


// Queries with parameters will need to be implemented slightly differently
// Ideally we want to be able to "capture" the variable defined within the closure
// Directives
class HeroNameAndFriends(
    val episode: String,
    builder: HeroNameAndFriends.() -> AbstractQuery
) : AbstractQuery() {

    override var typename: String = "query HeroNameAndFriends(" +
            "\$${::episode.name}: ${episode::class.java.simpleName}" +
            ")"

    init {
        builder()
    }
}

class HeroComparison(
    val id: Int = 3,
    val withFriends: Boolean = false,
    builder: HeroComparison.() -> AbstractQuery
) : AbstractQuery() {

    override var typename: String = "query HeroComparison(" +
            "\$${::id.name}: ${id::class.java.simpleName}, " +
            "\$${::withFriends.name}: ${withFriends::class.java.simpleName}" +
            ")"

    init {
        builder()
    }
}
