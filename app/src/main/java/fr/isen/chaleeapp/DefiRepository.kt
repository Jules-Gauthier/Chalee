package fr.isen.chaleeapp

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import fr.isen.chaleeapp.DefiRepository.Singleton.databaseRef
import fr.isen.chaleeapp.DefiRepository.Singleton.defiList
import javax.security.auth.callback.Callback

class DefiRepository {

    object Singleton{
        //se connecter à la référence defi qui contient nos données
        val databaseRef = FirebaseDatabase.getInstance().getReference("defis")

        //créer une liste qui va contenir nos defis
        val defiList = arrayListOf<DefiModel>()

    }

    fun updateData(callback: () -> Unit){

        // absorber les données depuis la databaseRef -> liste de défis
        databaseRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciens
                defiList.clear()
                //recolter la liste
                for (ds in snapshot.children) {
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

    //mettre a jour un objet defi dans la bdd
    fun updateDefi(defi: DefiModel) = databaseRef.child(defi.id).setValue(defi)

    //supprimer un defi de la base
    fun deleteDefi(defi: DefiModel) = databaseRef.child(defi.id).removeValue()
}