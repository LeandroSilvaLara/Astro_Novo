package br.com.leandro.astro.data.di

import android.util.Log
import br.com.leandro.astro.data.repository.PostRepository
import br.com.leandro.astro.data.repository.PostRepositoryImpl
import br.com.leandro.astro.data.services.SpaceFlightNewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Esse Object organiza as dependências da camada Data
 * Segue o mesmo padrão de injeção de dependência por camadas
 * adatado no PresentationModule, porém com mais componentes.
 */

object DataModule {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"
    private const val OK_HTTP = "Ok Http"

    fun load() {
        loadKoinModules(postsModule() + networkModule() )
    }

    /**
     * Criar uma instância de PostRepositoryImpl usando o service
     * mockado.
     */
    private fun postsModule() : Module {
        return module {
            single<PostRepository> { PostRepositoryImpl( get() ) }
        }
    }

    /**
     * Cria um módulo de rede
     */
    private fun networkModule() : Module {
        return module {

            /**
             * Criar um logging interceptor usando o OkHttp3
             */
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it, )
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            /**
             * Instancia uma factory do Moshi
             */
            single {
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            }

            /**
             * Cria o serviço de rede usando o Retrofit
             */
            single {
                createService<SpaceFlightNewsService>(get(), get())
            }

        }
    }

    /**
     * Essa função usa o Retrofit para criar um SpaceFlightNewsService.
     * Recebe como parâmetros um cliente OkHttp3 e uma factory do Moshi
     * para fazer a conversão dos dados.
     */
    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: Moshi,
    ) : T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .build()
            .create(T::class.java)
    }

}