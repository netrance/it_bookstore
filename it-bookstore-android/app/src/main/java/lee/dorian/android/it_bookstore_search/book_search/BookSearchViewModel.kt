package lee.dorian.android.it_bookstore_search.book_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import lee.dorian.android.it_bookstore_search.base.BaseViewModel
import lee.dorian.android.it_bookstore_search.data.repository.ITBookStoreRepositoryImpl
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient
import lee.dorian.android.it_bookstore_search.data.source.ITBookStoreRemoteDataSource
import lee.dorian.android.it_bookstore_search.domain.model.Book
import lee.dorian.android.it_bookstore_search.domain.usecase.RequestBookSearchUseCase

class BookSearchViewModel : BaseViewModel() {

    val query = MutableLiveData("")
    var keyword1 = ""
    var keyword2 = ""

    private var operator = ""

    private val _recentPage = MutableLiveData(1)

    private val _bookList = MutableLiveData<MutableList<Book>>(mutableListOf())
    val bookList: LiveData<MutableList<Book>> get() = _bookList

    // To check if all the books that meet query are loaded into the book list view
    private val _areAllSearched = MutableLiveData(false)
    val areAllSearched: LiveData<Boolean> get() = _areAllSearched

    private var bookSearchUseCase: RequestBookSearchUseCase

    init {
        _title.value = "IT Bookstore - Search"
        bookSearchUseCase = RequestBookSearchUseCase(
            ITBookStoreRepositoryImpl(ITBookStoreRemoteDataSource(ITBookStoreClient()))
        )
    }

    fun searchBooks(query: String) {

        if (query.contains("|")) {
            operator = "OR"
        }
        else if (query.contains("-")) {
            operator = "NOT"
        }
        else {
            operator = ""
        }

        if (operator.isEmpty()) {
            keyword1 = query
        }
        else {
            val keywords = query.split("|", "-")
            keyword1 = keywords[0]
            keyword2 = keywords[1]
        }

        _recentPage.value = 0
        _bookList.value?.clear()

        if (operator.isEmpty()) {
            searchBooksWith1Keyword()
        }
        else {
            searchBooksWith2Keywords()
        }
    }

    private fun searchBooksWith1Keyword() {
        bookSearchUseCase.run(keyword1, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe(
                bookListConsumer,
                errorConsumer
            )
            .let {
                disposables.add(it)
            }
    }

    private fun searchBooksWith2Keywords() {
        val bookSearchObservable1 = bookSearchUseCase.run(keyword1, 1).subscribeOn(Schedulers.io())
        val bookSearchObservable2 = bookSearchUseCase.run(keyword2, 1).subscribeOn(Schedulers.io())

        Single.zip(bookSearchObservable1, bookSearchObservable2, bookSearchZipper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe(
                bookListConsumer,
                errorConsumer
            )
            .also {
                disposables.add(it)
            }
    }

    fun appendBooks() {
        if ((null == _recentPage.value) or (null == keyword1) or (true == _areAllSearched.value)) {
            return
        }

        if (operator.isEmpty()) {
            appendBooksWith1Keyword()
        }
        else {
            appendBooksWith2Keywords()
        }
    }

    private fun appendBooksWith1Keyword() {
        bookSearchUseCase.run(keyword1, _recentPage.value!! + 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                bookListConsumer,
                errorConsumer
            )
            .also {
                disposables.add(it)
            }
    }

    private fun appendBooksWith2Keywords() {
        val bookSearchObservable1 = bookSearchUseCase.run(keyword1, _recentPage.value!! + 1)
        val bookSearchObservable2 = bookSearchUseCase.run(keyword2, _recentPage.value!! + 1)

        Single.zip(bookSearchObservable1, bookSearchObservable2, bookSearchZipper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe(
                bookListConsumer,
                errorConsumer
            )
            .let {
                disposables.add(it)
            }
    }

    private val bookSearchZipper = BiFunction<List<Book>, List<Book>, MutableList<Book>> { list1, list2 ->
        var resultBookList = mutableListOf<Book>()
        when (operator) {
            "OR" -> resultBookList = (list1 + list2).distinctBy { it.isbn13 } as MutableList<Book>
            "NOT" -> resultBookList = (list1 - list2).distinctBy { it.isbn13 } as MutableList<Book>
        }

        resultBookList
    }

    private val bookListConsumer = Consumer<List<Book>> {
        val existingBookList = _bookList.value ?: mutableListOf()
        existingBookList.addAll(it)
        _bookList.postValue(existingBookList)
        _recentPage.value = _recentPage.value!! + 1
        if (0 == it.size) {
            _areAllSearched.value = true
        }
    }

}