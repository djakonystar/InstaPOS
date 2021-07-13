package uz.texnopos.instagram.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.texnopos.instagram.MainActivity
import uz.texnopos.instagram.R
import uz.texnopos.instagram.data.Settings
import uz.texnopos.instagram.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding : FragmentSplashBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).setFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        val settings = Settings(requireContext())
        navController = Navigation.findNavController(view)

        binding.apply {
            splash.setMaxProgress(0.6f)
            splash.playAnimation()
            splash.addAnimatorListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    val action = if (settings.signedIn) {
                        SplashFragmentDirections.actionSplashFragmentToMainFragment()
                    } else {
                        SplashFragmentDirections.actionSplashFragmentToSignInFragment()
                    }
                    navController.navigate(action)
                    (activity as MainActivity).clearFullScreen()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }
            })
        }
    }
}