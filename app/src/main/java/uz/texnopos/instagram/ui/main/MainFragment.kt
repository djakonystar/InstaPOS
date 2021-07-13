package uz.texnopos.instagram.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import org.koin.android.ext.android.inject
import uz.texnopos.instagram.R
import uz.texnopos.instagram.data.Settings
import uz.texnopos.instagram.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val settings: Settings by inject()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings.signedIn = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.nav_home,
                R.id.nav_search,
                R.id.nav_add_post,
                R.id.nav_favorite,
                R.id.nav_profile
            ).build()
        navController = Navigation.findNavController(requireActivity(), R.id.mainFragmentNavHost)
        binding.bnv.setupWithNavController(navController)
    }
}