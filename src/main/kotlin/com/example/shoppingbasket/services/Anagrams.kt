package com.example.shoppingbasket.services

class Anagrams {

    fun anagrams(s: String): Set<String> {
        return generator("", s).toSet()
    }

    private fun generator(starter: String, leftover: String): List<String> {
        if (leftover.length <= 1) {
            return listOf(starter + leftover)
        }

        val answer = mutableListOf<String>()
        for (i in leftover.indices) {
            val newStarter = starter + leftover[i].toString()
            val newLeftover = leftover.replaceRange(IntRange(i, i), "")
            answer.addAll(generator(newStarter, newLeftover))
        }
        return answer
    }

}