package lee.dorian.android.it_bookstore_search.domain.usecase

import lee.dorian.android.it_bookstore_search.data.repository.ITBookStoreRepositoryImpl
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient
import lee.dorian.android.it_bookstore_search.data.source.ITBookStoreRemoteDataSource
import lee.dorian.android.it_bookstore_search.domain.model.Book
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RequestBookSearchUseCaseTest {

    lateinit var useCase: RequestBookSearchUseCase

    @Before
    fun setUp() {
        useCase  = RequestBookSearchUseCase(
            ITBookStoreRepositoryImpl(ITBookStoreRemoteDataSource(ITBookStoreClient()))
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun requestBookSearch() {
        useCase.run("android", 1).subscribe(
            { it: List<Book> ->
                Assert.assertNotNull(it)
                Assert.assertEquals(10, it.size)
            },
            { it: Throwable ->
                Assert.fail(it.message)
            }
        )
    }
}