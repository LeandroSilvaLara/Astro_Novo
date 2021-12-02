package br.com.leandro.astro.data.repository

import br.com.leandro.astro.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun listPosts() : Flow<List<Post>>
}