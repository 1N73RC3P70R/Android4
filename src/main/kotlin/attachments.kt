package ru.netology

interface Attachments {
    val type: String
}


data class attachmentPhoto(
    override val type: String = "photo",
    val photo: Photo
) : Attachments

data class attachmentVideo(
    override val type: String = "video",
    val video: Video
) : Attachments

data class attachmentImage(
    override val type: String = "image",
    var image: Image
) : Attachments

data class attachmentAdded(
    override val type: String = "added",
    var added: Added
) : Attachments

data class attachmentLive(
    override val type: String = "live",
    var live: Live
) : Attachments

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

data class PhotoAttachment(override val type: String = "photo", val photo: Photo) : Attachments
data class VideoAttachment(override val type: String = "video", val video: Video) : Attachments
data class ImageAttachment(override val type: String = "image", val image: Image) : Attachments
data class AddedAttachment(override val type: String = "added", val added: Added) : Attachments
data class LiveAttachment(override val type: String = "live", val live: Live) : Attachments