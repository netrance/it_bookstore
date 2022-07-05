package lee.dorian.android.it_bookstore_search.data.model

import lee.dorian.android.it_bookstore_search.domain.model.Book
import java.util.*

data class BookSearchResponse(
    val error: String,
    val total: String,
    val page: String,
    val books: List<BookEntity>
)