package lee.dorian.android.it_bookstore_search.domain.model

data class BookDetails(
    val title: String,
    val subtitle: String,
    val authors: String,
    val pulisher: String,
    val language: String,
    val isbn10: String,
    val isbn13: String,
    val pages: String,
    val year: String,
    val rating: String,
    val desc: String,
    val price: String,
    val image: String,
    val url: String
)
