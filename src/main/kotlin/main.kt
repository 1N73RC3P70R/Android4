package ru.netology

import ru.netology.WallService.posts


data class Post(
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String = " ",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Int = 0,
    val reposts: Reposts,
    val views: Views = Views(30)
)


data class Reposts(
    val count: Int,
    val userRepost: Int
)

data class Views(val count: Int = 0)


object WallService {
    var currentId = 0
    var posts = emptyArray<Post>()

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


fun getPosts(): List<Post> {
    return posts.toList()
}

fun main() {
    var viewsNew = Views()
    WallService.add(Post(0, 9, 0, 0, 29022024, reposts = Reposts(1, 1), views = viewsNew))

    viewsNew = Views(30)
    WallService.add(Post(1, 1, 1, 1, 1032024, reposts = Reposts(1, 1), views = viewsNew))

    WallService.add(Post(400, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(30)))
    WallService.update(Post(400, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(100)))

    val notFound = WallService.update(Post(401, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(150)))
    val found = WallService.update(Post(1, 1, 1, 1, 1032024, reposts = Reposts(1, 1), views = viewsNew))

    WallService.printPosts()
    println(
        "\nНайден? \n" +
                "Пост №1: $found\n" +
                "Пост №401: $notFound"
    )
}

