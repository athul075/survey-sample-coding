package com.sample.surveycomponent.appstart

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sample.surveycomponent.R


class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            val options = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment, null, options)
        }, 5000)


    }

}