package com.sample.surveycomponent.data.source

import com.sample.surveycomponent.data.Results
import com.sample.surveycomponent.data.SurveyItem
import com.sample.surveycomponent.data.User
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Implementation of the data source that adds a latency simulating network.
 */
object RemoteDataSource {

    private const val SERVICE_LATENCY_IN_MILLIS = 3000L

    private var USER_DATA = LinkedHashMap<Int, User>(2)
    private var SURVEY_LIST: List<SurveyItem>? = null

    init {
        addUser(1, "Austin","John")
        addSurveyListItems()
    }

    private fun addSurveyListItems() {
        SURVEY_LIST = arrayListOf<SurveyItem>(
            SurveyItem("Survey 1", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 2", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 3", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 4", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 5", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 6", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 7", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 8", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 9", "Lorem Ipsum is simply dummy text of the printing"),
            SurveyItem("Survey 10", "Lorem Ipsum is simply dummy text of the printing"),
        )
    }

    private fun addUser(userId: Int, firstName: String, lastName: String) {
        val newUser = User(userId,firstName,lastName)
        USER_DATA[newUser.userId] = newUser
    }

    suspend fun login(): Results<User> {
        // Simulate network by delaying the execution.
        val user = USER_DATA.values.toList()[0]
        delay(SERVICE_LATENCY_IN_MILLIS)
        return Results.Success(user)
    }

    suspend fun getSurveyList(userId: Int): Results<List<SurveyItem>?>{
        // Simulate network by delaying the execution.
        delay(SERVICE_LATENCY_IN_MILLIS)
        return Results.Success(SURVEY_LIST)
    }









}
