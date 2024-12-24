package com.example.simplegraphqlproject.data

data class Language(
    val name: String,
    val code: String
)

data class LanguageDetails(val name: String, val code: String, val rtl: Boolean, val native: String)