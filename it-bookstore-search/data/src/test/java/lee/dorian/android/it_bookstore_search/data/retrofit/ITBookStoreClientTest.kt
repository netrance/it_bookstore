package lee.dorian.android.it_bookstore_search.data.retrofit

import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ITBookStoreClientTest {

    lateinit var apiClient: ITBookStoreClient

    val isbn13ForTest = "9780321769626"

    @Before
    fun setUp() {
        apiClient = ITBookStoreClient()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun requestBookSearch() {
        apiClient.requestBookSearch("android", 1).subscribe(
            { it: BookSearchResponse ->
                Assert.assertEquals("0", it.error)
                Assert.assertEquals("1", it.page)
                Assert.assertNotNull(it.books)
                Assert.assertEquals(10, it.books.size)
            },
            { it: Throwable ->
                Assert.fail(it.message)
            }
        )
    }

    @Test
    fun requestBookDetails() {
        apiClient.requestBookDetails(isbn13ForTest).subscribe(
            {
                Assert.assertNotNull(it)
                Assert.assertEquals(isbn13ForTest, it.isbn13)
            },
            {
                Assert.fail(it.message)
            }
        )
    }

}