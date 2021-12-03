package br.com.leandro.astro

import android.app.Application
import br.com.leandro.astro.data.di.DataModule
import br.com.leandro.astro.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        /**
         * Carregar os módulos definidos para cada camada.
         * (Outros módulos devem ser adicionados aqui)
         */
        PresentationModule.load()
         DataModule.load()
    }
}