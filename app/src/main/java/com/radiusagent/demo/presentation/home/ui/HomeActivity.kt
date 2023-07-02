package com.radiusagent.demo.presentation.home.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiusagent.demo.R
import com.radiusagent.demo.data.database.DBHelper
import com.radiusagent.demo.data.remote.ApiManager
import com.radiusagent.demo.data.repository.FacilityRepositoryImpl
import com.radiusagent.demo.databinding.ActivityHomeBinding
import com.radiusagent.demo.domain.model.Facilities
import com.radiusagent.demo.domain.model.Facility
import com.radiusagent.demo.domain.use_case.facilities.FacilityUseCase
import com.radiusagent.demo.presentation.home.adapter.HomeAdapter
import com.radiusagent.demo.presentation.home.viewmodel.HomeViewModel
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var mAdapter: HomeAdapter
    private var facilities: Facilities? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        setAdapter()
        setupViewModel()
    }

    private fun setupViewModel() {
        homeViewModel = HomeViewModel(
            FacilityUseCase(
                FacilityRepositoryImpl(ApiManager.Instance.apiManagerInstance.getApiService()!!),
                DBHelper.getInstance(this)
            )
        )
        callGetFacilitiesApi()
        homeViewModel.getFacilitiesLiveData()?.observe(this) {
            if (it != null) {
                mAdapter.notifyListData(it)
            }
        }
    }

    private fun callGetFacilitiesApi() {
        if (homeViewModel.is24hoursCompleted()) homeViewModel.getFacilitiesData()
    }

    private fun setAdapter() {
        mAdapter = HomeAdapter()
        mBinding.rvList.layoutManager = LinearLayoutManager(this)
        mBinding.rvList.adapter = mAdapter
    }
}