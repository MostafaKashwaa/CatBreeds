package com.kashwaa.shared.cache

import com.kashwaa.shared.domain.Range
import com.squareup.sqldelight.ColumnAdapter

val rangeAdapter = object : ColumnAdapter<Range<Int>, String> {
    override fun decode(databaseValue: String): Range<Int> {
        runCatching {
            databaseValue
                .split('-')
                .map {
                    it.trim().toIntOrNull()
                }
        }.onSuccess {
            if (it.size == 2)
                return Range(it[0] ?: -1, it[1] ?: -1)
        }
        return Range(-1, -1)
    }

    override fun encode(value: Range<Int>) =
        "${value.min} - ${value.max}"
}

val listOfStringAdapter = object : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String) =
        if (databaseValue.isEmpty()) {
            listOf()
        } else {
            databaseValue.split(",")
        }

    override fun encode(value: List<String>): String {
        return value.joinToString(separator = ", ")
    }

}