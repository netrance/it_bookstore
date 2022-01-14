package lee.dorian.android.it_bookstore_search.book_search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import lee.dorian.android.it_bookstore_search.Const
import lee.dorian.android.it_bookstore_search.book_details.BookDetailsViewModel
import org.junit.*

class BookDetailsViewModelTest {

    // 라이브 데이터 테스트 하려면, 이 객체가 있어야 한다.
    // 참고 링크 - https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: BookDetailsViewModel

    val isbn13ForTest = "9780321769626"

    @Before
    fun setUp() {
        viewModel = BookDetailsViewModel()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun loadBookDetails() {
        viewModel.loadBookDetails(isbn13ForTest)
        Thread.sleep(Const.COMMON_DELAY_MILLISECONDS.toLong())
        Assert.assertNotNull(viewModel.bookDetails.value)
        Assert.assertEquals(isbn13ForTest, viewModel.bookDetails.value!!.isbn13)
    }

}