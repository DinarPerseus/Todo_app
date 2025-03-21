
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R

class NewsAdapter(articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val articles: List<Article> = articles

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.title.setText(article.title)
        holder.description.setText(article.description)

        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.imageView)

        holder.itemView.setOnClickListener { v: View ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(article.url))
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById<TextView>(R.id.newsTitle)
        var description: TextView = itemView.findViewById<TextView>(R.id.newsDescription)
        var imageView: ImageView = itemView.findViewById<ImageView>(R.id.newsImage)
    }
}