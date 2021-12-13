package com.sample.surveycomponent.survey.suveylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.surveycomponent.data.Results
import com.sample.surveycomponent.data.SurveyItem
import com.sample.surveycomponent.data.source.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {



    private val _isFetchingInProgress = MutableLiveData<Boolean>()
    val isFetchingInProgress: LiveData<Boolean> = _isFetchingInProgress

    private val _surveyList = MutableLiveData<List<SurveyItem>?>()
    val surveyList: LiveData<List<SurveyItem>?> = _surveyList


    fun getSurveyList() {

        // Show loading indicator
        _isFetchingInProgress.value = true



        viewModelScope.launch {

            repository.getSurveyList(0).let { result ->
                if (result is Results.Success<*>) {
                    onSurveyListFetched(result.data as? List<SurveyItem>)
                } else {
                    onDataNotAvailable()
                }
            }

            _isFetchingInProgress.value = false
        }
    }

    private fun onSurveyListFetched(surveyList: List<SurveyItem>?) {
        setList(surveyList)
    }

    private fun onDataNotAvailable() {
        _surveyList.value = null
        _isFetchingInProgress.value = false
    }

    private fun setList(surveyList: List<SurveyItem>?) {
        _surveyList.value = surveyList
        _isFetchingInProgress.value = surveyList != null
    }

}