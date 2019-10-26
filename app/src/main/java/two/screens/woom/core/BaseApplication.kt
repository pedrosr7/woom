package two.screens.woom.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import two.screens.woom.di.appModule
import two.screens.woom.di.networkModule


class BaseApplication : Application() {

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            androidLogger()
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(listOf(
                appModule, networkModule
            ))
        }
    }

}
