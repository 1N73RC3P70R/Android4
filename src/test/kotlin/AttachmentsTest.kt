import junit.framework.TestCase.assertEquals
import org.junit.Test
import ru.netology.*

class AttachmentsTest {
    @Test
    fun testAttachmentPhoto() {
        val photo = Photo(1, 1, "https://vk.com/some_photo_link", "https://vk.com/another_photo_link")
        val photoAttachment = attachmentPhoto(photo = photo)
        assertEquals("photo", photoAttachment.type)
        assertEquals(photo, photoAttachment.photo)
    }

    @Test
    fun testVideoAttachment() {
        val video = Video(1, 1, "Video", 60)
        val videoAttachment = attachmentVideo(video = video)
        assertEquals("video", videoAttachment.type)
        assertEquals(video, videoAttachment.video)
    }

    @Test
    fun testImageAttachment() {
        val image = Image(1, 1, 1920, 1080)
        val attachment = attachmentImage(image = image)
        assertEquals("image", attachment.type)
        assertEquals(image, attachment.image)
    }

    @Test
    fun testAddedAttachment() {
        val added = Added(1, 1, 10022024)
        val attachment = attachmentAdded(added = added)
        assertEquals("added", attachment.type)
        assertEquals(added, attachment.added)
    }

    @Test
    fun testLiveAttachment() {
        val live = Live(1, 1, 10022024, 11022024)
        val attachment = attachmentLive(live = live)
        assertEquals("live", attachment.type)
        assertEquals(live, attachment.live)
    }
}