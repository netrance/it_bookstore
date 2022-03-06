package lee.dorian.android.it_bookstore_search.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

open class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    // Will use title after refactoring (01/30th/2022)
    protected val _title = MutableLiveData("")
    val title: MutableLiveData<String> get() = _title

    // Will use isBackButtonVisible after refactoring (01/30th/2022)
    protected val _isBackButtonVisible = MutableLiveData(false)
    val isBackButtonVisible: MutableLiveData<Boolean> get() = _isBackButtonVisible

    protected val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    protected val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage

    protected val errorConsumer = Consumer<Throwable> {
        it.printStackTrace()
        _errorMessage.value = it.message
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}