package fr.isen.chaleeapp

import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.isen.chaleeapp.ProfileActivity.Singleton.databaseRef
import fr.isen.chaleeapp.ProfileActivity.Singleton.defiList
import fr.isen.chaleeapp.databinding.ActivityProfileBinding
import fr.isen.chaleeapp.databinding.ActivityRegisterBinding

class ProfileActivity {

    lateinit var binding: ActivityProfileBinding

    var interactor: ActivityProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root) //permet de créer une instance pour détecter toutes les interations que nous allons avoir avec le layout
    }

    object Singleton{
        //se connecter à la référence defi qui contient nos données
        val databaseRef = FirebaseDatabase.getInstance().getReference("defis")

        //créer une liste qui va contenir nos defis
        val defiList = arrayListOf<DefiModel>()

    }

    fun getData(callback: () -> Unit){

        // absorber les données depuis la databaseRef -> liste de défis
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciens
                defiList.clear()
                //recolter la liste de l'utilisateur
                for (ds in snapshot.children) {
                    //verifie si c'est les defis de l'utilisateur
                    if (ds.)
                    //construire un objet defi
                    val defi = ds.getValue(DefiModel::class.java)

                    // vérifier que le défi n'est pas null
                    if (defi != null){
                        //ajouter le defi à notre liste
                        defiList.add(defi)
                    }
                }

                // actionner le callback
                callback()

            }

            override fun onCancelled(error: DatabaseError) {}
            // en cas si il ne trouve pas les elements en questions

        })
    }

}