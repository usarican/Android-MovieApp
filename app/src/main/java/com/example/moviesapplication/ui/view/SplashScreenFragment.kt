package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.moviesapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(SPLASH_NAVIGATION_DELAY)
            navigateToMarketFragment(view)
        }

    }

    private fun navigateToMarketFragment(view : View) {
        Navigation.findNavController(view).navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMovieFragment())
    }

    companion object {
        private const val SPLASH_NAVIGATION_DELAY: Long = 2000
    }
}