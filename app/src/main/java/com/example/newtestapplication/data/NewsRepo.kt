package com.example.newtestapplication.data

import com.example.newtestapplication.BuildConfig
import com.example.newtestapplication.data.core.BaseRepository
import com.example.newtestapplication.data.core.Either
import com.example.theaterbookapp.data.core.MyException
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface NewsRepo {
    suspend fun getTopNews(country: String): Either<MyException, List<Article>>
}

class NewsRepoImpl(private val newsRepoService: NewsRepoService) : BaseRepository(),
    NewsRepo {
    override suspend fun getTopNews(country: String): Either<MyException, List<Article>> {
        return either {
            newsRepoService.getTopNews(country).articles
        }
    }
}

interface NewsRepoService {
    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY // this can be done in retrofit interceptor
    ): NewsRS
}

data class NewsRS(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: Date,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)