package com.mobile.programming.studentview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.mobile.programming.studentview.R
import com.mobile.programming.studentview.repository.Photo
import kotlinx.android.synthetic.main.gridview_item.view.*

class PhotoAdapter(private val photos: ArrayList<Photo>, private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return photos.size
    }

    override fun getItem(p0: Int): Any {
        return photos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return photos[p0].id.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val photo = this.photos[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.gridview_item, null)

        view.apply {
            txtPhoto.text = photo.title
            Glide.with(view.context)
                .load(photo.thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgPhoto)
        }
        return view
    }

    fun addPhotos(photos: List<Photo>){
        this.photos.apply {
            clear()
            addAll(photos)
        }
    }

}