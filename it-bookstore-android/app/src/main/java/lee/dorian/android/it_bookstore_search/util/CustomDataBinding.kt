package lee.dorian.android.it_bookstore_search.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lee.dorian.android.it_bookstore_search.book_search.BookListAdapter
import lee.dorian.android.it_bookstore_search.domain.model.Book

@BindingAdapter("bookList")
fun setItems(recyclerView: RecyclerView?, bookList: List<Book>?) {
    (recyclerView?.adapter as BookListAdapter?)?.let {
        if (bookList != null) {
            it.setBookList(bookList)
        }
    }
}

@BindingAdapter("android:src")
fun setSrc(imageView: ImageView?, url: String?) {
    if ((null != imageView) && (null != url)) {
        Glide.with(imageView).load(Uri.parse(url)).into(imageView)
    }
}
