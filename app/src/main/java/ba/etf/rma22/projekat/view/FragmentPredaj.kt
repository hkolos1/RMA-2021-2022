package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

class FragmentPredaj(var anketa: Anketa) : Fragment() {

    companion object {
        fun newInstance(
            anketa: Anketa,
            adapter: ViewPagerAdapter) =
            FragmentPredaj(anketa).apply {
                this.fragmentAdapter = adapter
            }
    }
    private lateinit var progressBar: TextView
    private lateinit var fragmentAdapter: ViewPagerAdapter
    private lateinit var predajDugme: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Funkcija za zaokruživanje na progressBar-u
    private fun zaokruziProgress(pro:Int):Int{
        var a = pro%20
        var noviProgress : Int
        if(a==0)
            return pro
        if(a<10)
            noviProgress = pro-a
        else noviProgress = pro+20-a
        if(noviProgress>100)
            noviProgress=100
        return noviProgress
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_predaj, container, false)

        predajDugme = view.findViewById(R.id.dugmePredaj)
        progressBar = view.findViewById(R.id.progresText)
        if(anketa.datumRada != null || anketa.datumKraj.before(Date())){
                predajDugme.isEnabled = true;
        }

        //Prikazuje postotak i progres ankete na zadnjem fragmentu
        progressBar.text = zaokruziProgress(anketa.progres).toString()+"%"


        //Predaje anketu i ispisuje poruku
        predajDugme.setOnClickListener() {
            fragmentAdapter.refreshFragment(1,FragmentPoruka.newInstance("Završili ste anketu ${anketa.naziv} u okviru istraživanja ${anketa.nazivIstrazivanja}"))

            //JAKO BITNO
            MainActivity.viewPager.setCurrentItem(1)

            fragmentAdapter.refreshFragment(0, FragmentAnkete.newInstance(fragmentAdapter))
            fragmentAdapter.remove(3)
            fragmentAdapter.remove(2)
            fragmentAdapter.notifyDataSetChanged()

        }
        return view;
    }
}