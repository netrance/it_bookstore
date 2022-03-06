package lee.dorian.android.it_bookstore_search.book_search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.disposables.CompositeDisposable
import lee.dorian.android.it_bookstore_search.Const
import lee.dorian.android.it_bookstore_search.base.BaseViewModel
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

    @Test
    fun onCleared() {
        viewModel.searchBooks("android")
        Thread.sleep(Const.COMMON_DELAY_MILLISECONDS.toLong())

        // Calls viewModel.onCleard()
        BookSearchViewModel::class.java.declaredMethods.first {
            it.name == "onCleared"
        }.also {
            it.isAccessible = true
            it.invoke(viewModel)
        }

        // Tests if viewModel.disposables.size() is equal to 0
        BaseViewModel::class.java.declaredFields.first {
            it.name == "disposables"
        }.also {
            it.isAccessible = true

            val disposables = it.get(viewModel) as CompositeDisposable
            Assert.assertEquals(0, disposables.size())
        }
    }

}