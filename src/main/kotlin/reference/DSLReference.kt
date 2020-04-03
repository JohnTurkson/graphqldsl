package reference

abstract class AbstractQuery {
    open var typename: String = ""
    
    internal val parameters: MutableList<String> = mutableListOf()
    
    fun hero(builder: HeroQuery.() -> HeroQuery): AbstractQuery {
        val query = HeroQuery()
        query.builder()
        parameters += query.toString()
        return this
    }
    
    fun hero(episode: String, builder: HeroQuery.() -> HeroQuery): AbstractQuery {
        val query = HeroQuery()
        query.builder()
        parameters += query.toString()
        return this
    }
    
    // fun hero(alias: String? = null, episode: String, builder: HeroQuery.() -> HeroQuery): AbstractQuery {
    //     val query = HeroQuery()
    //     query.builder()
    //     parameters += query.toString()
    //     return this
    // }
    
    fun human(id: String, builer: HumanQuery.() -> HumanQuery): AbstractQuery {
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

class Query(override var typename: String = "") : AbstractQuery() {
    infix fun named(name: String): Query {
        this.typename = "query " + name
        return this
    }
}

fun query(builder: Query.() -> AbstractQuery): Query {
    val query = Query()
    query.builder()
    return query
}

fun query(name: String = "", builder: Query.() -> AbstractQuery): Query {
    val query = Query(name)
    query.builder()
    return query
}

class HeroNameAndFriends(
    val episode: String,
    builder: HeroNameAndFriends.() -> AbstractQuery
) : AbstractQuery() {
    
    override var typename: String = "query HeroNameAndFriends"
    // override var typename: String = "query HeroNameAndFriends(" +
    //         "\$${::episode.name}: ${episode::class.java.simpleName}" +
    //         ")"
    
    init {
        builder()
    }
}

class HeroComparison(
    val id: Int = 3,
    val withFriends: Boolean = false,
    builder: HeroComparison.() -> AbstractQuery
) : AbstractQuery() {
    
    override var typename: String = "query HeroComparison"
    // override var typename: String = "query HeroComparison(" +
    //         "\$${::id.name}: ${id::class.java.simpleName}," +
    //         "\$${::withFriends.name}: ${withFriends::class.java.simpleName}" +
    //         ")"
    
    init {
        builder()
    }
}
