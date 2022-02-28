package fr.isen.chaleeapp

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.isen.chaleeapp.adapter.DefiAdapter

class DefiPopup(
    private val adapter : DefiAdapter,
    private val currentDefi : DefiModel
): Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_defi_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }


    private fun updateStar(button: ImageView){
        if(currentDefi.liked){
            button.setImageResource(R.drawable.ic_star)
        }else{
            button.setImageResource(R.drawable.ic_unstar)
        }


    }
    private fun setupStarButton() {
        //recuperer
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)


        //interaction
        starButton.setOnClickListener{
            currentDefi.liked = !currentDefi.liked
            val repo = DefiRepository()
            repo.updateDefi(currentDefi)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            //supprimer le défi de la bdd
            val repo = DefiRepository()
            repo.deleteDefi(currentDefi)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            //fermer la fenetre popup
            dismiss()
        }


    }

    private fun setupComponents() {
        // actualiser l'image du defi
        val defiImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentDefi.imageUrl)).into(defiImage)

        //actualiser le nom du défi
        findViewById<TextView>(R.id.popup_defi_name).text = currentDefi.name

        //actualiser le nom de la description
        findViewById<TextView>(R.id.popup_defi_description_subtitle).text = currentDefi.description

        //actualiser la difficulte du défi
        findViewById<TextView>(R.id.popup_defi_diffiiculte_subtitle).text = currentDefi.difficulte

        //actualiser le delai du défi
        findViewById<TextView>(R.id.popup_defi_delai_subtitle).text = currentDefi.delai


    }
}