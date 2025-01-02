package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.Coins

class Change {

    private val changeSets = mutableSetOf<Map<Coins, Int>>()

    fun calculateChange(sum: Int): Set<Map<Coins, Int>> {
        changeSets.clear()
        generateChange(emptyList(), sum)
        return changeSets

    }

    private fun generateChange(starter: List<Coins>, leftover: Int) {
        if (leftover == 0) {
            val change: Map<Coins, Int> = starter.groupingBy { coin -> coin }.eachCount().toSortedMap()
            synchronized(changeSets) {
                changeSets.add(change)
            }
        }
        else {
            for (coin in Coins.entries) {
                if (coin.value > leftover) {
                    continue
                }
                else {
                    val newStarter = starter + coin
                    val newLeftover = leftover - coin.value
                    generateChange(newStarter, newLeftover)
                }
            }
        }
    }
}