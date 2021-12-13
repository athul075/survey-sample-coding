package com.sample.surveycomponent.data.source


import android.util.Log
import com.sample.surveycomponent.data.Results
import com.sample.surveycomponent.data.SurveyItem
import com.sample.surveycomponent.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {


    override suspend fun login(username: String, password: String): Results<User> {
        return kotlinx.coroutines.withContext(ioDispatcher) {
            return@withContext performLogin()
        }
    }

    private suspend fun performLogin(): Results<User> {

        when (val user = remoteDataSource.login()) {
            is Error -> Log.e("Error", "Remote data source access failed")
            is Results.Success<*> -> {
                return user
            }
            else -> throw IllegalStateException()
        }

        return Results.Error(Exception("Error in Login"))


    }

    override  suspend fun getSurveyList(userId: Int): Results<List<SurveyItem>?> {
        when (val sureveyList = remoteDataSource.getSurveyList(userId)) {
            is Error -> Log.e("Error", "Remote data source access failed")
            is Results.Success -> {
                return sureveyList
            }
            else -> throw IllegalStateException()
        }

        return Results.Error(Exception("Error in fetching survey list"))
    }

}
