package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ie.wit.R
import ie.wit.models.DonationModel
import kotlinx.android.synthetic.main.card_donation.view.*

interface DonationListener {
    fun onDonationClick(donation: DonationModel)
    fun onFavouritesClick(donation: DonationModel)
}

class DonationAdapter constructor(var donations: ArrayList<DonationModel>,
                                  private val listener: DonationListener)
    : RecyclerView.Adapter<DonationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_donation,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val donation = donations[holder.adapterPosition]
        holder.bind(donation,listener)
    }

    override fun getItemCount(): Int = donations.size

    fun removeAt(position: Int) {
        donations.removeAt(position)
        notifyItemRemoved(position)
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(donation: DonationModel, listener: DonationListener) {
            itemView.tag = donation
            itemView.paymentamount.text = donation.amount.toString()
            itemView.paymentmethod.text = donation.paymenttype
            itemView.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
            if (donation.isfav) itemView.imagefavourite.setImageResource(R.drawable.star_big_on) else itemView.imagefavourite.setImageResource(R.drawable.star_big_off)
            itemView.imagefavourite.setOnClickListener {
                listener.onFavouritesClick(donation)
                if (donation.isfav)
                itemView.imagefavourite.setImageResource(R.drawable.star_big_off)
                else
                itemView.imagefavourite.setImageResource(R.drawable.star_big_on)
            }
            itemView.setOnClickListener { listener.onDonationClick(donation) }
        }
    }
}