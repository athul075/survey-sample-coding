package com.sample.surveycomponent.appstart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.surveycomponent.data.Results
import com.sample.surveycomponent.data.User
import com.sample.surveycomponent.data.source.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _isLoginInProgress = MutableLiveData<Boolean>()
    val isLoginInProgress: LiveData<Boolean> = _isLoginInProgress

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun doLogin() {
        login("", "")
    }

    fun login(userName: String, password: String) {

        // Show loading indicator
        _isLoginInProgress.value = true
        viewModelScope.launch {

            repository.login(userName, password).let { result ->
                if (result is Results.Success<*>) {
                    onLoginComplete(result.data as User)
                } else {
                    onDataNotAvailable(result)
                }
            }

            _isLoginInProgress.value = false
        }
    }

    private fun onLoginComplete(user: User) {
        setUser(user)
    }

    private fun onDataNotAvailable(result: Results<User>) {
        _user.value = null
        _isLoginInProgress.value = false
    }

    private fun setUser(user: User?) {
        this._user.value = user
        _isLoginInProgress.value = user != null
    }

}