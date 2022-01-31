package lee.dorian.android.it_bookstore_search.book_search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lee.dorian.android.it_bookstore_search.R
import lee.dorian.android.it_bookstore_search.base.BaseActivity
import lee.dorian.android.it_bookstore_search.book_details.BookDetailsActivity
import lee.dorian.android.it_bookstore_search.databinding.ActivityBookSearchBinding
import lee.dorian.android.it_bookstore_search.domain.model.Book

class BookSearchActivity : BaseActivity<ActivityBookSearchBinding, BookSearchViewModel>(R.layout.activity_book_search) {

    override val viewModel: BookSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            btnSearch.setOnClickListener(btnSearchClickListener)
            rvBookList.layoutManager = LinearLayoutManager(this@BookSearchActivity)
            rvBookList.addOnScrollListener(bookListScrollListener)
            rvBookList.adapter = BookListAdapter(bookItemClickListener)
        }

        binding.viewModel = viewModel.apply {
            errorMessage?.observe(this@BookSearchActivity, defaultErrorMessageObserver)
        }
    }

    val btnSearchClickListener = View.OnClickListener {
        binding.viewModel?.searchBooks(binding.etQuery.text.toString())
    }

    val bookItemClickListener = object: BookListAdapter.OnBookItemClickListener {
        override fun onClick(v: View, book: Book) {
            Intent(this@BookSearchActivity, BookDetailsActivity::class.java).apply {
                putExtra(BookDetailsActivity.INTENT_KEY_ISBN13, book.isbn13)
                startActivity(this)
            }
        }
    }

    private val bookListScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val bookListSize = binding.viewModel?.bookList?.value?.size ?: 0
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            if (layoutManager.findLastCompletelyVisibleItemPosition() == bookListSize - 1) {
                binding.viewModel?.appendBooks()
            }
        }
    }
}