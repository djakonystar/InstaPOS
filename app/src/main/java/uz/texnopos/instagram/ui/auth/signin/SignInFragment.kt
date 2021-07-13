package uz.texnopos.instagram.ui.auth.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.instagram.R
import uz.texnopos.instagram.data.ResourceState
import uz.texnopos.instagram.databinding.FragmentSignInBinding
import uz.texnopos.instagram.ui.auth.singup.SignUpFragmentDirections

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController
    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.apply {
            signUp.setOnClickListener {
                navController.navigate(R.id.action_signInFragment_to_signUpFragment)
            }

            btnSignIn.setOnClickListener {
                var success = true
                if (etEmail.text.isNullOrEmpty()) {
                    success = false
                    etEmail.error = getString(R.string.required_field)
                }
                if (etPassword.text.isNullOrEmpty()) {
                    success = false
                    etPassword.error = getString(R.string.required_field)
                }
                if (!success) return@setOnClickListener
                viewModel.signIn(etEmail.text.toString(), etPassword.text.toString())
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.signInStatus.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    val action = SignInFragmentDirections.actionSignInFragmentToMainFragment()
                    navController.navigate(action)
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            etEmail.isEnabled = !isLoading
            etPassword.isEnabled = !isLoading
        }
    }
}