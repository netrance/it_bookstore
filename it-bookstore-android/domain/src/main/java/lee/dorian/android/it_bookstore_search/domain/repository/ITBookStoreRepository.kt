package lee.dorian.android.it_bookstore_search.domain.repository

import io.reactivex.Flowable
import lee.dorian.android.it_bookstore_search.domain.model.Book
import lee.dorian.android.it_bookstore_search.domain.model.BookDetails

interface ITBookStoreRepository {

    fun requestBookSearch(query: String): Flowable<List<Book>>

    fun requestBookSearch(query: String, page: Int): Flowable<List<Book>>

    fun requestBookDetails(isbn13: String): Flowable<BookDetails>

}