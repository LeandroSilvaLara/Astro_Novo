package br.com.leandro.astro.data.di

import br.com.leandro.astro.data.repository.MockAPIService
import br.com.leandro.astro.data.repository.PostRepository
import br.com.leandro.astro.data.repository.PostRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Esse Object organiza as dependências da camada Data
 * Segue o mesmo padrão de injeção de dependência por camadas
 * adatado no PresentationModule, porém com mais componentes.
 */

object DataModule {

    fun load() {
        loadKoinModules(postsModule())
    }

    private fun postsModule() : Module {
        return module {
            single<PostRepository> { PostRepositoryImpl( MockAPIService ) }
        }
    }
}