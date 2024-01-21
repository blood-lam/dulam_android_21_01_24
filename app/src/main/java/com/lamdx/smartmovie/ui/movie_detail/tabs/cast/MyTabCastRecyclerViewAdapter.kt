package com.lamdx.smartmovie.ui.movie_detail.tabs.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lamdx.smartmovie.R
import com.lamdx.smartmovie.model.Cast
import com.lamdx.smartmovie.utils.Constant
import com.lamdx.smartmovie.utils.PosterSizes
import com.lamdx.smartmovie.utils.imagePresent

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTabCastRecyclerViewAdapter(
    private val values: List<Cast>,
    private val onClick: (Cast) -> Unit,
) : RecyclerView.Adapter<MyTabCastRecyclerViewAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_tab_cast, parent, false)

        return CastViewHolder(
            view, onClick
        )

    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class CastViewHolder(itemView: View, val onClick: (Cast) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val castImageView: ImageView = itemView.findViewById(R.id.tab_cast_poster)

        private var currentCast: Cast? = null

        init {
            itemView.setOnClickListener {
                currentCast?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(cast: Cast) {
            val profilePath = cast.profilePath
            profilePath?.let {
                val imgUrl =
                    "${Constant.IMG_HOST}/${PosterSizes.original.toString() + cast.profilePath}"
                imagePresent(itemView, imgUrl, castImageView)
            }

        }
    }

}