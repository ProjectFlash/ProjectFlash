import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.rkpandey.quizlet_wireframe.CardSetScreen
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.R

open class PostAdapter(val context: Context, val mList: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_set, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = mList.get(position)
        holder.bind(post)

        holder.cardView.setOnClickListener {
            val intent = (Intent(this.context, CardSetScreen::class.java))
            Log.i("Nuts", "Text: "+ post.getSetName())
            intent.putExtra("set", post.getSetName())
            startActivity(this.context, intent, null)

        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvUsername: TextView
        val tvSetName: TextView
        //val tvWord: TextView
        //val tvDefinition: TextView
        lateinit var cardView: CardView


        init {
            tvSetName= itemView.findViewById(R.id.set_name)
            //tvWord = itemView.findViewById(R.id.tvWord)
            tvUsername = itemView.findViewById(R.id.set_author)
            cardView = itemView.findViewById(R.id.card_view)
            //tvDefinition = itemView.findViewById(R.id.tvDefinition)
        }
        fun bind(post: Post) {
            tvUsername.text = post.getAuthor()
            tvSetName.text = post.getSetName()
            //tvWord.text = post.getWord().toString()
            //tvDefinition.text = post.getDefinition()
        }
    }
}