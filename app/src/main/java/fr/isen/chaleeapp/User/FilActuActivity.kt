package fr.isen.chaleeapp.User

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.chaleeapp.DefiRepository
import fr.isen.chaleeapp.R
import fr.isen.chaleeapp.fragments.HomeFragment

class FilActuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fil_actu)
        //charger notre DefiRepository
        val repo = DefiRepository()

        //mettre à jour la liste de defis
        repo.updateData{
            //injecter le fragment dans notre boite fragment_container
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}