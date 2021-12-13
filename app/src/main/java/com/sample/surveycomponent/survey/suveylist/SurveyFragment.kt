package com.sample.surveycomponent.survey.suveylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.surveycomponent.R
import com.sample.surveycomponent.databinding.FragmentSurveyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurveyFragment : Fragment(R.layout.fragment_survey_list) {

    private lateinit var adapter: SurveyRecyclerViewAdapter
    private val viewModel by viewModels<SurveyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val binding = FragmentSurveyListBinding.bind(view).apply {
            viewmodel = viewModel
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvSurvey.layoutManager = LinearLayoutManager(context)
        viewModel.getSurveyList()
        adapter = SurveyRecyclerViewAdapter()
        binding.rvSurvey.adapter = adapter
        observeListFetching()
    }

    private fun observeListFetching() {
        viewModel.surveyList.observe(this,{ list ->
            list?.let { adapter.add(it) }
        })
    }
}