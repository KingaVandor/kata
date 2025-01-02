package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.Month
import com.example.shoppingbasket.models.Month.FEBRUARY
import com.example.shoppingbasket.models.WeekDay
import com.example.shoppingbasket.models.WeekDay.FRIDAY
import com.example.shoppingbasket.models.WeekDay.MONDAY
import com.example.shoppingbasket.models.WeekDay.SATURDAY
import com.example.shoppingbasket.models.WeekDay.SUNDAY

class FiveWeekends {
    private val calendar = mutableMapOf<Int, Map<Month, Map<Int, WeekDay>>>()

    private val fiveDays = mutableMapOf<Int, List<Month>>()

    private val noFiveDayWeekends = mutableListOf<Int>()

    private fun hasFiveWeekends(daysOfMonth: Map<Int, WeekDay>): Boolean {
        val fridays = daysOfMonth.values.filter { it == FRIDAY }.toList().size
        val saturdays = daysOfMonth.values.filter { it == SATURDAY }.toList().size
        val sundays = daysOfMonth.values.filter { it == SUNDAY }.toList().size
        return fridays == 5 && saturdays == 5 && sundays == 5
    }

    private fun generateCalendar() {
        var currentWeekDay = MONDAY
        for (year in 1900..2100) {
            val monthsOfYear = mutableMapOf<Month, Map<Int, WeekDay>>()

            for (month: Month in Month.entries) {
                val daysOfMonth = mutableMapOf<Int, WeekDay>()
                val days = if ((year % 4 == 0 && year != 1900) && (month == FEBRUARY)) 29 else month.days
                for (day in 1..days) {
                    daysOfMonth[day] = currentWeekDay
                    currentWeekDay = currentWeekDay.next()
                }
                monthsOfYear[month] = daysOfMonth
                if (hasFiveWeekends(daysOfMonth)) fiveDays.merge(year, listOf(month), Collection<Month>::plus)
            }
            if (fiveDays[year] == null) noFiveDayWeekends.add(year)
        }
    }

    fun fiveWeekends(): Pair<Int, Int> {
        if (calendar.isEmpty()) generateCalendar()

        return Pair(fiveDays.values.sumOf { it.size }, noFiveDayWeekends.size)
    }
}