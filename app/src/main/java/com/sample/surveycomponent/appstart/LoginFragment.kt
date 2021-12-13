package com.sample.surveycomponent.appstart


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sample.surveycomponent.R
import com.sample.surveycomponent.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {


    private val viewModel by viewModels<StartupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewDataBinding = LoginFragmentBinding.bind(view).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.user.observe(this, {
            it?.let {
                navigateToSurveys()
            }
        })
    }

    private fun navigateToSurveys() {
        val options = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
        findNavController().navigate(R.id.action_loginFragment_to_survey_list, null, options)

    }

}