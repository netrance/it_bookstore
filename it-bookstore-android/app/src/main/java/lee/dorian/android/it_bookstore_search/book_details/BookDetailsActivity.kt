package lee.dorian.android.it_bookstore_search.book_details

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import lee.dorian.android.it_bookstore_search.R
import lee.dorian.android.it_bookstore_search.base.BaseActivity
import lee.dorian.android.it_bookstore_search.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : BaseActivity<ActivityBookDetailsBinding, BookDetailsViewModel>(R.layout.activity_book_details) {

    override val viewModel: BookDetailsViewModel by viewModels()

    companion object {
        const val INTENT_KEY_ISBN13 = "isbn13"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel?.let {
            val isbn13 = intent.getStringExtra(INTENT_KEY_ISBN13) ?: ""
            if (!isbn13.isEmpty()) {
                it.loadBookDetails(isbn13)
            }

            it.errorMessage?.observe(this@BookDetailsActivity, defaultErrorMessageObserver)
        }
    }

}