package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository.Companion.getUpisani
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.odabranaGrupa
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.odabranoIstrazivanje
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.porukaGrupa
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.porukaIstrazivanje
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.user
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

                spinnerIstrazivanje.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaIstrazivanja)

                istrazivanjeViewModel.getIstrazivanja(1,onSuccess = ::onSuccess, onError = ::onError)



                /*Spinner za Istrazivanje*/
                spinnerIstrazivanje.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        dugmeIstrazivanje.isEnabled = false
                    }
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        dugmeIstrazivanje.isEnabled = false
                        if (position == 0) {
                            odabranoIstrazivanje = 0
                            odabirIstrazivanja = 0
                        } else {
                            odabranoIstrazivanje = position;
                            odabirIstrazivanja = position
                        }
                        spinnerGrupa.adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, listaGrupa)


                        istrazivanjeViewModel.getGrupe(onSuccess = ::onSuccessG, onError = ::onError)


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
                                        /*userViewModel.dodajUpisanoIstrazivanje(
                                            listaGodina[odabirGodine],
                                            listaIstrazivanja[odabirIstrazivanja],
                                            listaGrupa[odabirGrupe]
                                        )*/
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

    fun onError() {
        val toast = Toast.makeText(context, "Error!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun onSuccess(anketica: List<Istrazivanje>) {
        val listaIstrazivanja = mutableListOf<String>()
        listaIstrazivanja.add("Istraživanje")
        if (user.trenutnaGodina != 0) {
            spinnerIstrazivanje.isEnabled = true
            var istrazivanjaSaGodine: MutableList<Istrazivanje> = mutableListOf<Istrazivanje>()
            for (P in anketica) {
                istrazivanjaSaGodine.add(P)
            }
            for (upisan in getUpisani()) {
                for (P in anketica) {
                    if (upisan.naziv == P.naziv) {
                        istrazivanjaSaGodine.remove(upisan)
                    }
                }
            }
            for (P in istrazivanjaSaGodine) {
                listaIstrazivanja.add(P.naziv)
            }
            spinnerIstrazivanje.adapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                anketica.map { p: Istrazivanje -> p.naziv.toString() })
            spinnerIstrazivanje.setSelection(odabranoIstrazivanje)
        } else {
            spinnerIstrazivanje.isEnabled = false
        }
        val toast = Toast.makeText(context, "Istraživanja were found.", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun onSuccessG(anketica: List<Grupa>) {
        val listaGrupa = mutableListOf<String>()
        listaGrupa.add("Grupa")
        if (odabranoIstrazivanje != 0) {
            spinnerGrupa.isEnabled = true
            for (P in anketica) {
                listaGrupa.add(P.naziv)
            }
            spinnerGrupa.adapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                anketica.map { p: Grupa -> p.naziv.toString() })
            spinnerGrupa.setSelection(odabranaGrupa)
        } else {
            spinnerGrupa.isEnabled = false
        }
        val toast = Toast.makeText(context, "Grupe were found.", Toast.LENGTH_SHORT)
        toast.show()
    }
}