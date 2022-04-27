package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository.Companion.getUpisani
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.odabranaGrupa
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.odabranoIstrazivanje
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.porukaGrupa
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.porukaIstrazivanje
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.user
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import ba.etf.rma22.projekat.viewmodel.UserViewModel


class FragmentIstrazivanje : Fragment() {
    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            FragmentIstrazivanje().apply {
                this.fragmentAdapter = adapter
            }
    }
    private lateinit var fragmentAdapter: ViewPagerAdapter
    private lateinit var spinnerGodina: Spinner
    private lateinit var spinnerIstrazivanje: Spinner
    private lateinit var spinnerGrupa: Spinner
    private var istrazivanjeViewModel: IstrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel: GrupaViewModel = GrupaViewModel()
    private var userViewModel: UserViewModel = UserViewModel()
    private lateinit var dugmeIstrazivanje: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_istrazivanje, container, false)

        //dohvacanje elemenata
        dugmeIstrazivanje = view.findViewById(ba.etf.rma22.projekat.R.id.dodajIstrazivanjeDugme)
        spinnerGodina = view.findViewById(ba.etf.rma22.projekat.R.id.odabirGodina)
        spinnerIstrazivanje = view.findViewById(ba.etf.rma22.projekat.R.id.odabirIstrazivanja)
        spinnerGrupa = view.findViewById(ba.etf.rma22.projekat.R.id.odabirGrupa)

        dugmeIstrazivanje.isEnabled = false

        var odabirGodine: Int
        var odabirIstrazivanja: Int
        var odabirGrupe: Int

        //Umjesto praznog stringa postavljanje placeholdera
        val listaGodina = listOf<String>("Godina", "1", "2", "3", "4", "5")
        val listaIstrazivanja = listOf<String>("Istraživanje")
        val listaGrupa = listOf<String>("Grupa")
        spinnerGodina.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaGodina)
        spinnerIstrazivanje.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaIstrazivanja)
        spinnerGrupa.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaGrupa)


        if (user.trenutnaGodina != 0) {
            spinnerGodina.setSelection(user.trenutnaGodina)
        }

        /*Spinner za Godine*/
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                dugmeIstrazivanje.isEnabled = false
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (listaGodina[position] == "Godina") {
                    user.trenutnaGodina = 0
                    odabirGodine = 0
                } else {
                    user.trenutnaGodina = listaGodina[position].toInt()
                    odabirGodine = position
                }
                dugmeIstrazivanje.isEnabled = false
                val listaIstrazivanja = mutableListOf<String>()
                listaIstrazivanja.add("Istraživanje")
                spinnerIstrazivanje.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaIstrazivanja)

                if (user.trenutnaGodina != 0) {
                    spinnerIstrazivanje.isEnabled = true
                    var istrazivanjaSaGodine: MutableList<Istrazivanje> = mutableListOf<Istrazivanje>()
                    for (P in istrazivanjeViewModel.getIstrazivanjaByGodina(listaGodina[position].toInt())) {
                        istrazivanjaSaGodine.add(P)
                    }
                    for (upisan in getUpisani()) {
                        for (P in istrazivanjeViewModel.getIstrazivanjaByGodina(listaGodina[position].toInt())) {
                            if (upisan.naziv == P.naziv) {
                                istrazivanjaSaGodine.remove(upisan)
                            }
                        }
                    }
                    for (P in istrazivanjaSaGodine) {
                        listaIstrazivanja.add(P.naziv)
                    }
                    spinnerIstrazivanje.adapter = ArrayAdapter(
                        inflater.context,
                        android.R.layout.simple_list_item_1,
                        listaIstrazivanja
                    )
                    spinnerIstrazivanje.setSelection(odabranoIstrazivanje)
                } else {
                    spinnerIstrazivanje.isEnabled = false
                }

                /*Spinner za Istrazivanje*/
                spinnerIstrazivanje.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        dugmeIstrazivanje.isEnabled = false
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        dugmeIstrazivanje.isEnabled = false
                        if (listaIstrazivanja[position] == "Istraživanje") {
                            odabranoIstrazivanje = 0
                            odabirIstrazivanja = 0
                        } else {
                            odabranoIstrazivanje = position;
                            odabirIstrazivanja = position
                        }
                        val listaGrupa = mutableListOf<String>()
                        listaGrupa.add("Grupa")
                        spinnerGrupa.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaGrupa)


                        if (odabranoIstrazivanje != 0) {
                            spinnerGrupa.isEnabled = true
                            for (P in grupaViewModel.getGroupsByIstrazivanje(listaIstrazivanja[position])) {
                                listaGrupa.add(P.naziv)
                            }
                            spinnerGrupa.adapter = ArrayAdapter(
                                inflater.context,
                                android.R.layout.simple_list_item_1,
                                listaGrupa
                            )
                            spinnerGrupa.setSelection(odabranaGrupa)
                        } else {
                            spinnerGrupa.isEnabled = false
                        }

                        /*Spinner za Grupu*/
                        spinnerGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                dugmeIstrazivanje.isEnabled = false
                            }
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                if (parent!!.getItemAtPosition(position).equals("Grupa")) {
                                    dugmeIstrazivanje.isEnabled = false
                                    odabranaGrupa = 0
                                } else {
                                    odabirGrupe = position
                                    odabranaGrupa = position
                                    dugmeIstrazivanje.isEnabled = true

                                    /*Dugme za upis na istraživanje i grupu*/
                                    dugmeIstrazivanje.setOnClickListener() {

                                        //Istraživanje
                                        porukaIstrazivanje = listaIstrazivanja[odabirIstrazivanja]
                                        //Grupa
                                        porukaGrupa = listaGrupa[odabirGrupe]
                                        userViewModel.dodajUpisanoIstrazivanje(
                                            listaGodina[odabirGodine],
                                            listaIstrazivanja[odabirIstrazivanja],
                                            listaGrupa[odabirGrupe]
                                        )
                                        odabranoIstrazivanje = 0
                                        odabranaGrupa = 0
                                        fragmentAdapter.refreshFragment(1, FragmentPoruka.newInstance(""))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return view;
    }
}