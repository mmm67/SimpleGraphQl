package com.example.simplegraphqlproject.data

interface LanguageClient {
    suspend fun getLanguages(): List<Language>
    suspend fun getLanguageDetails(code: String): LanguageDetails?
}