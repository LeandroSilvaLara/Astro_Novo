package br.com.leandro.astro.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.leandro.astro.data.model.Post
import br.com.leandro.astro.data.repository.PostRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PostRepository) : ViewModel() {


    private val _listPost = MutableLiveData<List<Post>>()
    val listPost: LiveData<List<Post>>
        get() = _listPost

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            repository.listPosts().collect {
                _listPost.value = it
            }
        }
    }
    val helloText = StringBuilder().apply{
        _listPost.value?.let { list ->
            append("There are ${list.size} posts:")
            appendLine()
            list.forEach { post ->
                appendLine("--- start post ---")
                append("${post.title}")
                appendLine()
                append("${post.summary}")
                appendLine()
            }

        }
    }
}