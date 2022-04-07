package com.ubaya.ubayakost160419147.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.ubayakost160419147.R
import kotlinx.android.synthetic.main.fragment_pusat_bantuan.*

/**
 * A simple [Fragment] subclass.
 * Use the [PusatBantuanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PusatBantuanFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pusat_bantuan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnWhatsapp.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data =
                Uri.parse("https://api.whatsapp.com/send?phone=081336305166&text=Tolong Bantu Saya")
            val shareIntent = Intent.createChooser(sendIntent,"Send My Text")
            startActivity(shareIntent)
        }
    }
}