package ba.etf.rma22.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Pitanje

class FragmentPitanje(pitanje: Pitanje, adapter: ViewPagerAdapter) : Fragment() {
    companion object {
        fun newInstance(pitanje: Pitanje, adapter: ViewPagerAdapter): FragmentPitanje =
            FragmentPitanje(pitanje,adapter).apply {
                this.fragmentAdapter = adapter
            }
    }

    var prviOdabir: Boolean = true
    private lateinit var fragmentAdapter: ViewPagerAdapter
    private var pitanje = pitanje
    private lateinit var tekst: TextView
    private lateinit var listaOdgovora: ListView
    private lateinit var dugmeZaustavi: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_pitanje, container, false)

        tekst = view.findViewById(R.id.tekstPitanja)
        tekst.text = pitanje.tekstPitanja
        listaOdgovora = view.findViewById(R.id.odgovoriLista)
        dugmeZaustavi = view.findViewById(R.id.dugmeZaustavi)
        listaOdgovora.adapter =
            ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, pitanje.opcije)

        listaOdgovora.setOnItemClickListener{ parent: AdapterView<*>, view: View, position: Int, id ->
            //oboji samo prvo kliknuto polje
            if(prviOdabir){
                (view as TextView).setTextColor(Color.parseColor("#0000FF"))
                prviOdabir = false;
            }
        }

        //Dugme zaustavlja se pojavljuje na svakom fragmentu i vraća na FragmentAnkete bez ikakve poruke?!
        dugmeZaustavi.setOnClickListener() {
            MainActivity.viewPager.setCurrentItem(0)
            fragmentAdapter.refreshFragment(0, FragmentAnkete.newInstance(fragmentAdapter))
            fragmentAdapter.refreshFragment(1, FragmentIstrazivanje.newInstance(fragmentAdapter))
            fragmentAdapter.remove(2)
            fragmentAdapter.remove(2)
            fragmentAdapter.notifyDataSetChanged()
        }
        return view;
    }
}