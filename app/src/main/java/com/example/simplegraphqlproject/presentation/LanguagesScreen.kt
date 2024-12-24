package com.example.simplegraphqlproject.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.simplegraphqlproject.R
import com.example.simplegraphqlproject.data.Language
import com.example.simplegraphqlproject.data.LanguageDetails

@Composable
fun LanguagesScreen(
    modifier: Modifier = Modifier,
    languageState: LanguageViewModel.LanguageUiState,
    onLanguageSelected: (String) -> Unit
) {
    var expandedItem by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        if (languageState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = modifier.padding(20.dp)) {
                items(languageState.languages) { language ->
                    LanguageItem(
                        language = language,
                        isExpanded = expandedItem == language.name,
                        onClick = {
                            expandedItem = if (expandedItem == language.name) {
                                null
                            } else {
                                onLanguageSelected(language.code)
                                language.name
                            }
                        }
                    )
                    if (expandedItem == language.name) {
                        LanguageDetailsSection(languageState.selectedLanguage)
                    }
                }
            }
        }
    }
}

@Composable
fun LanguageDetailsSection(language: LanguageDetails?) {
    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("RTL: ")
                }
                append(language?.rtl.toString())
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Native: ")
                }
                append(language?.native)
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Code: ")
                }
                append(language?.code)
            }
        )
    }
}


@Composable
fun LanguageItem(
    language: Language,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(R.drawable.language),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.weight(1f),
            text = language.name,
            style = MaterialTheme.typography.headlineLarge
        )

        Icon(
            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
    }
}
