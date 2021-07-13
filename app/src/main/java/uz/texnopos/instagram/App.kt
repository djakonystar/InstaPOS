package uz.texnopos.instagram

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.texnopos.instagram.di.dataModule
import uz.texnopos.instagram.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(dataModule, viewModelModule)
        startKoin {
            androidLogger()

            androidContext(this@App)

            androidFileProperties()

            koin.loadModules(modules)
        }
    }
}