package lee.dorian.android.it_bookstore_search.book_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import lee.dorian.android.it_bookstore_search.base.BaseViewModel
import lee.dorian.android.it_bookstore_search.data.repository.ITBookStoreRepositoryImpl
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient
import lee.dorian.android.it_bookstore_search.data.source.ITBookStoreRemoteDataSource
import lee.dorian.android.it_bookstore_search.domain.model.BookDetails
import lee.dorian.android.it_bookstore_search.domain.usecase.RequestBookDetailsUseCase

class BookDetailsViewModel : BaseViewModel() {

    val _bookDetails = MutableLiveData<BookDetails>()
    val bookDetails: LiveData<BookDetails> get() = _bookDetails

    private val bookDetailsUseCase = RequestBookDetailsUseCase(
        ITBookStoreRepositoryImpl(
            ITBookStoreRemoteDataSource(
                ITBookStoreClient()
            )
        )
    )

    fun loadBookDetails(isbn13: String) {
        bookDetailsUseCase.run(isbn13)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe(bookDetailsConsumer, errorConsumer)
    }

    private val bookDetailsConsumer = Consumer<BookDetails> { bookDetails ->
        _bookDetails.postValue(bookDetails)
    }

}