package lee.dorian.android.it_bookstore_search.data.source

import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ITBookStoreRemoteDataSourceTest {

    lateinit var apiClient: ITBookStoreClient
    lateinit var remoteDataSource: ITBookStoreRemoteDataSource

    val isbn13ForTest = "9780321769626"

    @Before
    fun setUp() {
        apiClient = ITBookStoreClient()
        remoteDataSource = ITBookStoreRemoteDataSource(apiClient)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun searchBooks() {
        remoteDataSource.searchBooks("android", 1).subscribe(
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
    fun readBookDetails() {
        remoteDataSource.readBookDetails(isbn13ForTest).subscribe(
            { bookDetailsResponse ->
                Assert.assertEquals("0", bookDetailsResponse.error)
                Assert.assertEquals(isbn13ForTest, bookDetailsResponse.isbn13)
            },
            {
                Assert.fail(it.message)
            }
        )
    }

}