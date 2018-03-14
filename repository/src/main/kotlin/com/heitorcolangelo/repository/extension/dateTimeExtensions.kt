package com.heitorcolangelo.repository.extension

import com.heitorcolangelo.repository.model.DateType

fun DateType.simpleDate(): String = with(localDateTime) {
    "${this.monthValue}/${this.dayOfMonth}/${this.year}"
}