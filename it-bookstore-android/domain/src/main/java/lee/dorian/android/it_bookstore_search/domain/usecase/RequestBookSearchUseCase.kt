package lee.dorian.android.it_bookstore_search.domain.usecase

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.domain.model.Book
import lee.dorian.android.it_bookstore_search.domain.repository.ITBookStoreRepository

class RequestBookSearchUseCase(val repository: ITBookStoreRepository) {

    fun run(query: String): Single<List<Book>> {
        return run(query, 1)
    }

    fun run(query: String, page: Int): Single<List<Book>> {
        return repository.requestBookSearch(query, page)
    }

}