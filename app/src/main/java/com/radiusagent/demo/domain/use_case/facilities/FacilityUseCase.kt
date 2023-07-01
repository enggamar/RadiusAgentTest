package com.radiusagent.demo.domain.use_case.facilities

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.radiusagent.demo.common.base_response.Result
import com.radiusagent.demo.data.database.DBHelper
import com.radiusagent.demo.data.remote.NetworkCallback
import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.repository.FacilityRepositry
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Throwable


class FacilityUseCase(private val repo: FacilityRepositry, val dbHelper: DBHelper) {

    /**
     * This function is used to call facilities api from network
     */
    fun callFacilitiesApi() {
        repo.callFacilitiesApi()?.enqueue(object : NetworkCallback<Facilities?>() {
            override fun onResponse(result: Result<Facilities?>?) {
                insertFacilitiesIntoDb(result?.data)
            }
        })
    }

    /**
     * This function is used to insert the facilities into the Data base
     */
    @SuppressLint("CheckResult")
    private fun insertFacilitiesIntoDb(data: Facilities?) {
        Observable.fromCallable {
            if (data != null) {
                dbHelper.getRadiusDao().insert(data)
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                // Handle the result if needed
                Log.d("DB_ERROR", result.toString())
            }, { error ->
                error.message?.let { Log.d("DB_ERROR", it) }
            })
    }

    /**
     * This function is to get the live data observer of facility DB table
     */
    fun getFacilitiesLiveData(): LiveData<Facilities?>? {
        return dbHelper.getRadiusDao().getProducts()
    }
}


