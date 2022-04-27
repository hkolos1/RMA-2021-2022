package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.porukaGrupa
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.porukaIstrazivanje

class FragmentPoruka(var odabir: String) : Fragment() {
    companion object {
        fun newInstance(odabir: String): FragmentPoruka = FragmentPoruka(odabir)
    }

    private lateinit var message: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        pager: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_poruka, pager, false)
        message = view.findViewById(R.id.tvPoruka)

        if (odabir == "") {
            var prikaziPoruku = "Uspješno ste upisani u grupu " + porukaGrupa +
                    " istraživanja " + porukaIstrazivanje + "!"
            message.setText(prikaziPoruku)
        } else {
            message.setText(odabir)
        }
        return view

    }
}