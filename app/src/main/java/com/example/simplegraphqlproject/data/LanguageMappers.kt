package com.example.simplegraphqlproject.data

import com.example.GetLanguageDetailsQuery
import com.example.GetLanguagesQuery

fun GetLanguagesQuery.Language.toLanguage(): Language {
    return Language(name = name, code = code)
}

fun GetLanguageDetailsQuery.Language.toLanguageDetails(): LanguageDetails {
    return LanguageDetails(name = name, code = code, rtl = rtl, native = native)
}