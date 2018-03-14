package com.heitorcolangelo.repository.data.remote.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.heitorcolangelo.repository.model.DateType
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.io.IOException

class DateTypeAdapter : TypeAdapter<DateType>() {

    @Throws(IOException::class)
    override fun read(source: JsonReader): DateType? {
        if (!source.hasNext()) return null

        if (source.peek() == JsonToken.NULL) {
            source.skipValue()
            return null
        }

        val date = source.nextString()
        val dateTime = LocalDateTime.parse(date, API_FORMAT)
        return DateType(dateTime)
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: DateType?) {
        if (value != null) out.value(value.apiFormat)
    }

    companion object {
        //DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC)
        val API_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX").withZone(ZoneOffset.UTC)
    }
}