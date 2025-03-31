package util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId

//fun formatTimestamp(timeStamp:String):String{
//    val isoTimestamp = timeStamp.replace(" ", "T")
//    val parsedTimestamp = OffsetDateTime.parse(isoTimestamp)
//    val formatter = DateTimeFormatter.ofPattern("dd MMM yy")
//    val formattedTimestamp = parsedTimestamp.format(formatter)
//    return formattedTimestamp
//}


fun formatTimestamp(timeStamp: String): String {
    val parsedTimestamp = OffsetDateTime.parse(timeStamp.replace(" ", "T"))
    val localTime = parsedTimestamp.atZoneSameInstant(ZoneId.systemDefault())
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yy")
    val formattedTime = localTime.format(timeFormatter)
    val formattedDate = localTime.format(dateFormatter)
    return "$formattedTime, $formattedDate"
}