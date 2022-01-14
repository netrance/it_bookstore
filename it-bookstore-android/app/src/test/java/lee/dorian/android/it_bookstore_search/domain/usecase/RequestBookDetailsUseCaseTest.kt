package lee.dorian.android.it_bookstore_search.domain.usecase

import lee.dorian.android.it_bookstore_search.data.repository.ITBookStoreRepositoryImpl
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient
import lee.dorian.android.it_bookstore_search.data.source.ITBookStoreRemoteDataSource
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RequestBookDetailsUseCaseTest {

    lateinit var useCase: RequestBookDetailsUseCase

    val isbn13ForTest = "9780321769626"

    @Before
    fun setUp() {
        useCase  = RequestBookDetailsUseCase(
            ITBookStoreRepositoryImpl(ITBookStoreRemoteDataSource(ITBookStoreClient()))
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun run() {
        useCase.run(isbn13ForTest).subscribe(
            {
                Assert.assertNotNull(it)
                Assert.assertEquals(isbn13ForTest, it.isbn13)
            },
            { it: Throwable ->
                Assert.fail(it.message)
            }
        )
    }
}