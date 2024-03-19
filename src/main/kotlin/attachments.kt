package ru.netology

interface Attachments {
    val attachments: List<Attachment>
}

sealed class Attachment(val type: String)
data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int
)

data class Image(
    val id: Int,
    val ownerId: Int,
    val xAxisPixels: Int,
    val yAxisPixels: Int
)

data class Added(
    val id: Int,
    val ownerId: Int,
    val dateAdded: Int,
)

data class Live(
    val id: Int,
    val ownerId: Int,
    val startTime: Int,
    val endTime: Int
)

data class PhotoAttachment(val photo: Photo) : Attachment("photo")
data class VideoAttachment(val video: Video) : Attachment("video")
data class ImageAttachment(val image: Image) : Attachment("image")
data class AddedAttachment(val added: Added) : Attachment("added")
data class LiveAttachment(val live: Live) : Attachment("live")