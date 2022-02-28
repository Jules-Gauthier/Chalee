package fr.isen.chaleeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.chaleeapp.fragments.HomeFragment
import fr.isen.chaleeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //permet de créer une instance pour détecter toutes les interations que nous allons avoir avec le layout

        binding.buttonIdentification.setOnClickListener {
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent) //méthode qui démarre l'intent
        }

        binding.buttonCreation.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent) //méthode qui démarre l'intent
        }

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