package com.example.shoppingbasket.services

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


class KataServiceCodeWars {

    fun catchTheBus(busTimes: Pair<String, String>, boyTimes: Pair<String, String>): Double {
        val boyStart = getTimeStamp(boyTimes.first)
        val busStart = getTimeStamp(busTimes.first)
        val boyEnd = getTimeStamp(boyTimes.second)
        val busEnd = getTimeStamp(busTimes.second)

        var boyCurrent = boyStart
        var missed = 0
        var counter = 0

        while (boyCurrent <= boyEnd) {
            var busCurrent = busStart

            while (busCurrent <= busEnd) {
                if (boyCurrent > busCurrent) {
                    missed ++
                }
                busCurrent += 10
                counter ++
            }
            boyCurrent += 10
        }

        return   BigDecimal(missed.toDouble() * 100 /counter.toDouble()).setScale(3, RoundingMode.HALF_EVEN).toDouble()
    }

    private  fun getTimeStamp(inputTime: String): Long {
        val time = if (inputTime.length == 8 ) inputTime.dropLast(3)
        else "0" + inputTime.dropLast(3)

        var dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(time))
        if (inputTime.contains("PM")) dateTime = dateTime.plusHours(12)
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    fun incrementString(str: String) : String {
        println("input: $str")
        val alphabet = "[^0-9]".toRegex()
        val lastBit = str.split(alphabet).last()

        return if (lastBit.isEmpty() ) str + "1"
        else {
            val endNum = lastBit.toInt() + 1
            str.dropLast(lastBit.length) + lastBit.dropLast(endNum.toString().length) + endNum
        }
    }
}

//val info = "28 + 32 * 2 / 64 = 29"
//val regex = "\\D+".toRegex()
//assertThat(info.split(regex)).containsExactly("28", "32", "2", "64", "29")












