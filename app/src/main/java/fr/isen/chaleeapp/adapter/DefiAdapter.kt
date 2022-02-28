package fr.isen.chaleeapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.isen.chaleeapp.*

class DefiAdapter(
    val context:MainActivity,
    private val defiList: List<DefiModel>,
    private val layoutId: Int) : RecyclerView.Adapter<DefiAdapter.ViewHolder>(){
    //boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //image du défi
        val defiImage = view.findViewById<ImageView>(R.id.image_item)
        val defiName:TextView? = view.findViewById(R.id.name_item)
        val defiDescription: TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId,parent,false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les informations du defi
        val currentDefi = defiList[position]

        //recuperer le repository
        val repo = DefiRepository()

        //utiliser gide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentDefi.imageUrl)).into(holder.defiImage)

        //mettre à jour le nom du défi
        holder.defiName?.text = currentDefi.name

        //mettre à jour la description du défi
        holder.defiDescription?.text = currentDefi.description

        // vérifier si le défi a été liker ou non
         if (currentDefi.liked) {
             holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
             holder.starIcon.setImageResource(R.drawable.ic_unstar)

         }
        //rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener{
            //inverser si le bouton est like ou non
            currentDefi.liked = !currentDefi.liked
            //mettre à jour l'objet defi
            repo.updateDefi(currentDefi)

        }
        //interaction lors du clic sur un défi
        holder.itemView.setOnClickListener{
            // afficher la popup
            DefiPopup(this,currentDefi).show()

        }

    }

    override fun getItemCount(): Int = defiList.size
}