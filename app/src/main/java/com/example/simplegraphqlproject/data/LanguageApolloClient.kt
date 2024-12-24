package com.example.simplegraphqlproject.data

import com.apollographql.apollo.ApolloClient
import com.example.GetLanguageDetailsQuery
import com.example.GetLanguagesQuery

class LanguageApolloClient(private val apolloClient: ApolloClient) : LanguageClient {
    override suspend fun getLanguages(): List<Language> {
        return apolloClient.query(GetLanguagesQuery()).execute().data?.languages?.map {
            it.toLanguage()
        } ?: emptyList()
    }

    override suspend fun getLanguageDetails(code: String): LanguageDetails? {
        return apolloClient.query(GetLanguageDetailsQuery(code))
            .execute().data?.language?.toLanguageDetails()
    }

}