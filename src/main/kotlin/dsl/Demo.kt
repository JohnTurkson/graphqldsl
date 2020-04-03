package dsl


fun main() {
    // // Fields
    // val firstQuery = query {
    //     hero {
    //         name
    //     }
    // }
    // println("firstQuery:")
    // println(firstQuery)
    // println()
    //
    // // query {
    // //
    // // }
    //
    // // query {
    // //     hero {
    // //
    // //     }
    // // }
    //
    // val nestedQuery = query {
    //     hero {
    //         name
    //         // Queries can have comments!
    //         friends {
    //             name
    //         }
    //     }
    // }
    // println("nestedQuery:")
    // println(nestedQuery)
    // println()
    //
    // // Arguments
    // val queryWithArguments = query {
    //     human(id = "1000") {
    //         name
    //         height
    //     }
    // }
    // println("queryWithArguments:")
    // println(queryWithArguments)
    // println()
    //
    // val queryWithNestedArguments = query {
    //     human(id = "1000") {
    //         name
    //         height(unit = "FOOT")
    //         // height("FOOT")
    //         // height()
    //     }
    // }
    // println("queryWithNestedArguments:")
    // println(queryWithNestedArguments)
    // println()
    //
    // val locale = "US"
    // val queryWithConditionalArguments = query {
    //     human("999") {
    //         name
    //         val localeSpecificUnit = if (locale == "US") "FOOT" else "METER"
    //         height(localeSpecificUnit)
    //     }
    // }
    // println("queryWithConditionalArguments:")
    // println(queryWithConditionalArguments)
    // println()
    //
    // // Operation name
    // val alternateAliasedQuery = query("AlternateAliasedQuery") {
    //     hero {
    //         name
    //     }
    // }
    //
    // println("alternateAliasedQuery:")
    // println(alternateAliasedQuery)
    // println()
    //
    // // Variables
    // val queryWithVariables = HeroNameAndFriends(episode = "EMPIRE") {
    //     hero(episode) {
    //         name
    //         friends {
    //             name
    //         }
    //     }
    // }
    //
    // println("queryWithVariables:")
    // println(queryWithVariables)
    // println()
    //
    // // Directives
    // val queryWithDirectives = HeroComparison(id = 2) {
    //     // validation using language syntax
    //     if (id < 0) throw Exception("invalid id")
    //
    //     hero {
    //         // conditions using language syntax
    //         if (withFriends) {
    //             friends {
    //                 name
    //             }
    //         }
    //         name
    //     }
    // }
    //
    // println("queryWithDirectives:")
    // println(queryWithDirectives)
    // println()
}
