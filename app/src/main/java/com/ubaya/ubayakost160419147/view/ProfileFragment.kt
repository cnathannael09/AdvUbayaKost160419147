package com.ubaya.ubayakost160419147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.ubayakost160419147.R
import com.ubaya.ubayakost160419147.util.loadImage
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtNamaProfile.setText("Christian Nathannael")
        txtTelepon.setText("081336305166")
        imgProfile.loadImage("https://i.pinimg.com/564x/33/4f/f3/334ff327269dad96ddc6a8f6a32e6393.jpg",progressLoadingProfile)

        btnAbout.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileAbout()
            Navigation.findNavController(it).navigate(action)
        }

        btnBantuan.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfilePusat()
            Navigation.findNavController(it).navigate(action)
        }

        btnSyarat.setOnClickListener{
            val action = ProfileFragmentDirections.actionProfileSyarat()
            Navigation.findNavController(it).navigate(action)
        }
    }
}