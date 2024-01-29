package com.mmreda.mohamedredatask.presentation.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developnetwork.universe.utils.DiffUtilCallBack
import com.mmreda.mohamedredatask.data.remote.dto.home.PostDto
import com.mmreda.mohamedredatask.databinding.ItemPostsBinding

class PostsAdapter(
    private val onItemPostClicked: OnItemPostClicked
) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    private var postList = emptyList<PostDto>()

    class MyViewHolder(private val itemBinding: ItemPostsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(post: PostDto) {

            itemBinding.textTitleValue.text = post.title

            itemBinding.textBodyValue.text = post.body

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        MyViewHolder(itemBinding)

        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postList[position]

        holder.bind(post)

        holder.itemView.setOnClickListener {
            onItemPostClicked.onItemClicked(post)
        }
    }

    fun saveData(newPostList: List<PostDto>) {
        val unitListDiffUtil = DiffUtilCallBack(postList, newPostList)
        val diffUtilResult = DiffUtil.calculateDiff(unitListDiffUtil)
        postList = newPostList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        postList = emptyList()
    }
}