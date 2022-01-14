package lee.dorian.android.it_bookstore_search.book_search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.functions.Consumer
import lee.dorian.android.it_bookstore_search.Const
import lee.dorian.android.it_bookstore_search.domain.model.Book
import org.junit.*

class BookSearchViewModelTest {

    // 라이브 데이터 테스트 하려면, 이 객체가 있어야 한다.
    // 참고 링크 - https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: BookSearchViewModel

    @Before
    fun setUp() {
        viewModel = BookSearchViewModel()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun searchBooks() {
        viewModel.searchBooks("android")
        Thread.sleep(Const.COMMON_DELAY_MILLISECONDS.toLong())
        Assert.assertEquals(10, viewModel.bookList.value?.size)
    }

}