package lee.dorian.android.it_bookstore_search.domain.repository

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.domain.model.Book
import lee.dorian.android.it_bookstore_search.domain.model.BookDetails

interface ITBookStoreRepository {

    fun requestBookSearch(query: String): Single<List<Book>>

    fun requestBookSearch(query: String, page: Int): Single<List<Book>>

    fun requestBookDetails(isbn13: String): Single<BookDetails>

}