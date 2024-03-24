import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.*

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

    @Test
    fun shouldAddCommentToExistingPost() {
        val post = Post(1, 1, 1, 1, 1032024, reposts = Reposts(1, 1), views = Views(30))
        WallService.add(post)

        val comment = Comment(1, 1, 24032024, "Пост 1")
        WallService.createComment(1, comment)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrowExceptionForNonExistingPost() {
        val comment = Comment(2, 1, 23032024, "Пост 2")
        WallService.createComment(2, comment)
    }

    @Test
    fun `checkForSpam should return false for non-spam text`() {
        val text = "Спам 1"
        val result = WallService.checkForSpam(text)
        assertFalse(result)
    }

    @Test
    fun `checkForSpam should return true for spam text`() {
        val text = "Спам 2"
        val result = WallService.checkForSpam(text)
        assertTrue(result)
    }
}
