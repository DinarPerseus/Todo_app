import com.google.gson.annotations.SerializedName

class NewsResponse {
    @SerializedName("status")
    val status: String? = null

    @SerializedName("totalResults")
    val totalResults: Int = 0

    @SerializedName("articles")
    val articles: List<Article>? = null
}

class Article {
    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("url")
    val url: String? = null

    @SerializedName("urlToImage")
    val urlToImage: String? = null
}