package com.example.simplegraphqlproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apollographql.apollo.ApolloClient
import com.example.simplegraphqlproject.data.LanguageApolloClient
import com.example.simplegraphqlproject.presentation.LanguageViewModel
import com.example.simplegraphqlproject.presentation.LanguageViewModelFactory
import com.example.simplegraphqlproject.presentation.LanguagesScreen
import com.example.simplegraphqlproject.ui.theme.SimpleGraphQlProjectTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val BASE_URL = "https://countries.trevorblades.com/graphql"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleGraphQlProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val apolloClient = ApolloClient.Builder()
                        .serverUrl(BASE_URL)
                        .build()

                    val languageViewModel = ViewModelProvider(
                        this,
                        LanguageViewModelFactory(LanguageApolloClient(apolloClient))
                    )[LanguageViewModel::class.java]

                    val languageState by languageViewModel.uiState.collectAsState()
                    LanguagesScreen(
                        modifier = Modifier.padding(innerPadding),
                        languageState,
                        onLanguageSelected = { languageViewModel.onLanguageSelected(it) })
                }
            }
        }
    }
}
