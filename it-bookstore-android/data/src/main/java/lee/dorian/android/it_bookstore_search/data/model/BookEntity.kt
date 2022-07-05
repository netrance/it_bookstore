package lee.dorian.android.it_bookstore_search.data.model

import lee.dorian.android.it_bookstore_search.domain.model.Book
import java.util.*

data class BookEntity(
    val title: String,
    val subtitle: String,
    val isbn13: String,
    val price: String,
    val image: String,
    val url: String
) {

    fun toDTO(): Book = Book(
        title,
        subtitle,
        isbn13,
        price,
        image,
        url
    )

}
