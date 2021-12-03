package br.com.leandro.astro.data.services

import br.com.leandro.astro.data.model.Post
import retrofit2.http.GET

/**
 * Essa interface é responsável pela comunicação com API web
 */


interface SpaceFlightNewsService {
    /**
     * Esse endpoint acessa uma lista de artigos
     * Ele não possui parâmetros.
     */

    @GET("articles")
    suspend fun listPosts() : List<Post>
}