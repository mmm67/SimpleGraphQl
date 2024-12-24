package com.example.simplegraphqlproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.example.simplegraphqlproject.data.Language
import com.example.simplegraphqlproject.data.LanguageApolloClient
import com.example.simplegraphqlproject.data.LanguageDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LanguageViewModel(private val apolloClient: LanguageApolloClient) : ViewModel() {

    data class LanguageUiState(
        val isLoading: Boolean = false,
        val languages: List<Language> = emptyList(),
        val selectedLanguage: LanguageDetails? = null
    )

    private var _uiState = MutableStateFlow(LanguageUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(languages = apolloClient.getLanguages(), isLoading = false)
            }
        }
    }

    fun onLanguageSelected(code: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(selectedLanguage = apolloClient.getLanguageDetails(code))
            }
        }
    }
}