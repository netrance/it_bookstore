package lee.dorian.android.it_bookstore_search.data.repository

import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient
import lee.dorian.android.it_bookstore_search.data.source.ITBookStoreRemoteDataSource
import lee.dorian.android.it_bookstore_search.domain.model.Book
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ITBookStoreRepositoryImplTest {
    lateinit var apiClient: ITBookStoreClient
    lateinit var remoteDataSource: ITBookStoreRemoteDataSource
    lateinit var itBookStoreRepositoryImpl: ITBookStoreRepositoryImpl

    val isbn13ForTest = "9780321769626"

    @Before
    fun setUp() {
        apiClient = ITBookStoreClient()
        remoteDataSource = ITBookStoreRemoteDataSource(apiClient)
        itBookStoreRepositoryImpl = ITBookStoreRepositoryImpl(remoteDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun requestBookSearch() {
        itBookStoreRepositoryImpl.requestBookSearch("android", 1).subscribe(
            { bookList ->
                Assert.assertNotNull(bookList)
                Assert.assertEquals(10, bookList.size)
            },
            { throwable ->
                Assert.fail(throwable.message)
            }
        )
    }

    @Test
    fun requestBookDetails() {
        itBookStoreRepositoryImpl.requestBookDetails(isbn13ForTest).subscribe(
            { bookDetails ->
                Assert.assertNotNull(bookDetails)
                Assert.assertEquals(isbn13ForTest, bookDetails.isbn13)
            },
            { throwable ->
                Assert.fail(throwable.message)
            }
        )
    }

}