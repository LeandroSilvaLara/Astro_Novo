package br.com.leandro.astro.data.di

import br.com.leandro.astro.data.repository.PostRepository
import br.com.leandro.astro.data.repository.PostRepositoryImpl
import br.com.leandro.astro.data.services.SpaceFlightNewsService
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Esse Object organiza as dependências da camada Data
 * Segue o mesmo padrão de injeção de dependência por camadas
 * adatado no PresentationModule, porém com mais componentes.
 */

object DataModule {

    private const val BASE_URL = "https://api.spaceflightnewapi.net/v3/"

    fun load() {
        loadKoinModules(postsModule())
    }

    /**
     * Criar uma instância de PostRepositoryImpl usando o service
     * mockado.
     */
    private fun postsModule() : Module {
        return module {
            single<PostRepository> { PostRepositoryImpl( createService() ) }
        }
    }

    private inline fun createService() : SpaceFlightNewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
            .create(SpaceFlightNewsService::class.java)
    }
}