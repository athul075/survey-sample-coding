package com.sample.surveycomponent.data.source

import com.sample.surveycomponent.data.Results
import com.sample.surveycomponent.data.SurveyItem
import com.sample.surveycomponent.data.User
import javax.inject.Inject

interface Repository {

    suspend fun login(username: String, password: String): Results<User>
    suspend fun getSurveyList(userId: Int): Results<List<SurveyItem>?>

}
