import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.netology.Post
import ru.netology.Reposts
import ru.netology.Views
import ru.netology.WallService

class WallServiceTest {

    @Before
    fun setUp() {
        WallService.clear()
    }

    @Test
    fun testAddPost() {
        val post = Post(0, 1, 2, 3, 4, " ", 5, 6, 7, Reposts(0, 0), Views(0))
        val addedPost = WallService.add(post)
        assertEquals(0, addedPost.id)
    }

    @Test
    fun testUpdatePostTrue() {
        val post = Post(400, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(30))
        WallService.add(post)
        val updatedPost = Post(400, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(100))
        val result = WallService.update(updatedPost)
        assertEquals(true, result)
    }

    @Test
    fun testUpdatePostFalse() {
        val updatedPost = Post(401, 1, 1, 2, 10032024, reposts = Reposts(1, 1), views = Views(150))
        val result = WallService.update(updatedPost)
        assertEquals(false, result)
    }
}
