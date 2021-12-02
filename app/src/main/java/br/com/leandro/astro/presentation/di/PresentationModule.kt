package br.com.leandro.astro.presentation.di

import br.com.leandro.astro.presentation.ui.home.HomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    /**
     * Essa função é que fica exposta publicamente para ser chamada
     * na classe App. É possível concatenar vários módulos usando o
     * operador "+". Isso reduz a duplicação de código na classe App.
     */
    fun load() {
        loadKoinModules(viewModelModule())
    }

    /**
     * Instancia os Viewmodels da camada de apresentação.
     * A função get() indica ao Koin que ele precisa buscar uma
     * dependência (nesse caso, do DataModule)
     */
    // TODO 006: Adicionar a dependência do HomeViewModel com get()
    private fun viewModelModule() : Module {
        return module {
            factory { HomeViewModel() }
        }
    }

}