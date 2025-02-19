package com.example.tintaph

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private val events: List<Event>,
    private val onBookmarkClick: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
        private val eventName: TextView = itemView.findViewById(R.id.eventName)
        private val eventDateTime: TextView = itemView.findViewById(R.id.eventDateTime)
        private val eventDescription: TextView = itemView.findViewById(R.id.eventDescription)
        private val bookmarkButton: ImageButton = itemView.findViewById(R.id.bookmarkButton)

        fun bind(event: Event) {
            eventImage.setImageResource(event.imageResId)
            eventName.text = event.name
            eventDateTime.text = event.dateTime
            eventDescription.text = event.description

            // Update bookmark button text
            bookmarkButton.setImageResource(
                if (event.isBookmarked) R.drawable.int_bk else R.drawable.int_b
            )

            // Handle bookmark button click
            bookmarkButton.setOnClickListener {
                onBookmarkClick(event)
            }
        }
    }
}
