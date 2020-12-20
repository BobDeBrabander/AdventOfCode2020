package day19

data class RuleNode(
    val ruleNumber: Int,
    var rule: String
) {
    var parsedRegex: String? = null
}