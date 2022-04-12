package ba.etf.rma22.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.view.AnketaAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var ankete: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var anketaAdapter: AnketaAdapter
    private lateinit var upisDugme: FloatingActionButton
    private var anketaViewModel = AnketaViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ankete = findViewById(R.id.listaAnketa)

        ankete.layoutManager = GridLayoutManager(this, 2)
        anketaAdapter = AnketaAdapter(arrayListOf())
        ankete.adapter = anketaAdapter
        anketaAdapter.updateAnkete(anketaViewModel.getAll())

        spinner = findViewById(R.id.filterAnketa)
        val opcije = listOf("Sve moje ankete", "Sve ankete", "Urađene ankete", "Buduće ankete", "Prošle ankete")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcije)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        upisDugme = findViewById(R.id.upisDugme)
        upisDugme.setOnClickListener{
            val intent = Intent(this, UpisIstrazivanje::class.java)
            startActivity(intent)
        }
    }
}