package uz.texnopos.instagram.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.texnopos.instagram.data.FirebaseHelper
import uz.texnopos.instagram.data.Settings
import uz.texnopos.instagram.ui.auth.signin.SignInViewModel
import uz.texnopos.instagram.ui.auth.singup.SignUpViewModel

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseHelper(get()) }
    single { Settings(androidContext()) }
}

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
}