package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel

class FragmentAnkete : Fragment() {
    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            FragmentAnkete().apply {
                this.fragmentAdapter = adapter
            }
    }
    private lateinit var fragmentAdapter: ViewPagerAdapter
    private lateinit var ankete: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var anketaAdapter: AnketaAdapter
    private var anketaViewModel = AnketaViewModel()

    //Funkcija za odabir tipa Ankete
    private fun izaberiAnketu(position: Int){
        if(position==0){
            anketaAdapter.updateAnkete(anketaViewModel.getMyAnkete())
        }
        else if(position==1){
            anketaAdapter.updateAnkete(anketaViewModel.getAll())
        }
        else if(position==2){
            anketaAdapter.updateAnkete(anketaViewModel.getDone())
        }
        else if(position==3){
            anketaAdapter.updateAnkete(anketaViewModel.getFuture())
        }
        else if(position==4){
            anketaAdapter.updateAnkete(anketaViewModel.getNotTaken())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_ankete, container, false)
        super.onCreate(savedInstanceState)
        ankete = view.findViewById(R.id.listaAnketa)

        ankete.layoutManager = GridLayoutManager(activity, 2)
        anketaAdapter = AnketaAdapter(arrayListOf()) { anketa-> dodajPitanja(anketa) }
        //anketaAdapter = AnketaAdapter(arrayListOf(), dodaj())
        ankete.adapter = anketaAdapter
        anketaAdapter.updateAnkete(anketaViewModel.getAll())

        spinner = view.findViewById(R.id.filterAnketa)
        val opcije = listOf("Sve moje ankete", "Sve ankete", "Urađene ankete", "Buduće ankete", "Prošle ankete")
        spinner.adapter = ArrayAdapter(ankete.context, android.R.layout.simple_list_item_1, opcije)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                izaberiAnketu(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        return view;
    }

    override fun onResume() {
        super.onResume()
        izaberiAnketu(0)
    }

    //Funkcija za load pitanja iz Liste i navigiranje kroz fragmente pitanja
    fun dodajPitanja(Anketa: Anketa){
        if(!AnketaRepository.getMyAnkete().contains(Anketa)){
            var lista : List<Pitanje>
            lista = PitanjeAnketaRepository.getPitanja(Anketa.naziv, Anketa.nazivIstrazivanja)
            fragmentAdapter.refreshFragment(0,FragmentPitanje.newInstance(lista[0],fragmentAdapter))
            fragmentAdapter.refreshFragment(1,FragmentPitanje.newInstance(lista[1],fragmentAdapter))
            fragmentAdapter.add(2,FragmentPitanje.newInstance(lista[2], fragmentAdapter))
            fragmentAdapter.add(3, FragmentPredaj.newInstance(Anketa , fragmentAdapter))
            fragmentAdapter.notifyDataSetChanged()
        }
    }
}