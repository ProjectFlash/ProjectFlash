import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rkpandey.quizlet_wireframe.ItemsViewModel
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.R

class PostAdapter(val mList: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_set, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {

        val post = mList.get(position)
        holder.bind(post)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvUsername: TextView
        val tvPostBody: TextView
        val tvDefinition: TextView

        init {
            tvUsername= itemView.findViewById(R.id.tvUsername)
            tvPostBody = itemView.findViewById(R.id.tvPostBody)
            tvDefinition = itemView.findViewById(R.id.tvDefinition)
        }

        fun bind(post: Post) {
            tvUsername.text = post.getUser()?.username
            tvPostBody.text = post.getWord()
            tvDefinition.text = post.getDefinition()
        }
    }
}