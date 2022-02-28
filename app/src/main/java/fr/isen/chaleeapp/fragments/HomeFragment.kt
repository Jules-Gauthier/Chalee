package fr.isen.chaleeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.isen.chaleeapp.DefiRepository.Singleton.defiList
import fr.isen.chaleeapp.MainActivity
import fr.isen.chaleeapp.R
import fr.isen.chaleeapp.User.FilActuActivity
import fr.isen.chaleeapp.adapter.DefiAdapter
import fr.isen.chaleeapp.adapter.DefiItemDecoration


class HomeFragment(
    private val context: FilActuActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)


        // recuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = DefiAdapter(context, defiList,R.layout.item_horizontal_defi)

        // recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = DefiAdapter(context, defiList,R.layout.item_verticale_defi)
        verticalRecyclerView.addItemDecoration(DefiItemDecoration())
        return view

    }
}