package com.example.barnbook.data.remote

import io.ktor.client.HttpClient

class apiService(private val httpClient: HttpClient) {
    suspend fun getHomeScreenDetails() {}
    suspend fun getProfileScreenDetails() {}
    suspend fun getActivityScreenDetails() {}
    suspend fun getItemsScreenDetails() {}
}