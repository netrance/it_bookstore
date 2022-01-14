package lee.dorian.android.it_bookstore_search.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _title = MutableLiveData("")
    val title: MutableLiveData<String> get() = _title

    protected val _isBackButtonVisible = MutableLiveData(false)
    val isBackButtonVisible: MutableLiveData<Boolean> get() = _isBackButtonVisible

    protected val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    protected val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage

}