package ru.netology

import ru.netology.WallService.posts

fun main() {
    var viewsNew = Views()
    WallService.add(Post(0, 9, 0, 0, 29022024, reposts = Reposts(1, 1), views = viewsNew))

    viewsNew = Views(30)
    WallService.add(Post(1, 1, 1, 1, 1032024, reposts = Reposts(1, 1), views = viewsNew))
    WallService.add(Post(2, 1, 1, 1, 1032024, reposts = Reposts(1, 1), views = viewsNew))

    WallService.add(Post(400, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(30)))
    WallService.update(Post(400, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(100)))

    val notFound = WallService.update(Post(404, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(150)))
    val found = WallService.update(Post(1, 1, 1, 1, 1032024, reposts = Reposts(1, 1), views = viewsNew))

    WallService.printPosts()
    println(
        "\nНайден? \n" +
                "Пост №1: $found\n" +
                "Пост №404: $notFound\n\n\n"
    )


    val photo = Photo(1, 1, "https://vk.com/some_photo_link", "https://vk.com/another_photo_link")
    val photoAttachment = PhotoAttachment(photo = photo)

    val video = Video(1, 1, "Video", 60)
    val videoAttachment = VideoAttachment(video = video)

    val image = Image(1, 1, 1920, 1080)
    val imageAttachment = ImageAttachment(image = image)

    val added = Added(1, 1, 10022024)
    val addedAttachment = AddedAttachment(added = added)

    val live = Live(1, 1, 10022024, 11022024)
    val liveAttachment = LiveAttachment(live = live)

    val attachments = listOf(photoAttachment, videoAttachment, imageAttachment, addedAttachment, liveAttachment)
    println("$attachments")

    val comment1 = Comment(1, 1, 24032024, "Пост 1")
    val comment2 = Comment(2, 1, 23032024, "Пост 2")

    WallService.createComment(1, comment1)
    WallService.createComment(2, comment2)

    WallService.comments
    WallService.comments.forEach { println(it) }
}

data class Post(
    var id: Int?,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String? = " ",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Int = 0,
    val reposts: Reposts,
    val views: Views = Views(30),
    val attachments: List<Attachment> = emptyList()
)

data class Comment(
    val postId: Int,
    val fromId: Int,
    val date: Int,
    val text: String
)

enum class Reason(val code: Int, val description: String) {
    SPAM(0, "Спам"),
}

data class Reposts(
    val count: Int,
    val userRepost: Int
)

data class Views(val count: Int = 0)

object WallService {
    var posts = emptyArray<Post>()
    var comments = emptyArray<Comment>()

    fun createComment(postId: Int, comment: Comment): Comment {
        posts.find { it.id == postId } ?: throw PostNotFoundException("Пост $postId не найден.")

        if (checkForSpam(comment.text)) {
            throw SpamDetectedException("Внимание, спам!")
        }

        comments += comment
        return comment
    }

    fun checkForSpam(text: String): Boolean {
        return text.contains("Спам")
    }

    fun add(post: Post): Post {
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post.copy(views = post.views.copy())
                return true
            }
        }
        return false
    }

    fun printPosts() {
        for (post in posts) {
            println(post)
        }
    }

    fun clear() {
        posts = emptyArray()
    }
}

class PostNotFoundException(messageIdNotFound: String) : Exception(messageIdNotFound)

class SpamDetectedException(message: String) : Exception(message)

fun getPosts(): List<Post> {
    return posts.toList()
}
