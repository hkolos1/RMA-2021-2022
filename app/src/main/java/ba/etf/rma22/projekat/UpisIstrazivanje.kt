package ba.etf.rma22.projekat

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.user
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import ba.etf.rma22.projekat.viewmodel.UserViewModel

class UpisIstrazivanje : AppCompatActivity() {

    private lateinit var spinnerGodina: Spinner
    private lateinit var spinnerIstrazivanje: Spinner
    private lateinit var spinnerGrupa: Spinner
    private var istrazivanjeViewModel : IstrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel : GrupaViewModel = GrupaViewModel()
    private var userViewModel : UserViewModel = UserViewModel()
    private lateinit var dugmeIstrazivanje : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ba.etf.rma22.projekat.R.layout.activity_upis_istrazivanje)

        //dohvacanje elemenata
        dugmeIstrazivanje = findViewById(ba.etf.rma22.projekat.R.id.dodajIstrazivanjeDugme)
        spinnerGodina = findViewById(ba.etf.rma22.projekat.R.id.odabirGodina)
        spinnerIstrazivanje = findViewById(ba.etf.rma22.projekat.R.id.odabirIstrazivanja)
        spinnerGrupa = findViewById(ba.etf.rma22.projekat.R.id.odabirGrupa)

        dugmeIstrazivanje.isEnabled=false

        var odabirGodine : Int
        var odabirIstrazivanja : Int
        var odabirGrupe : Int

        //Umjesto praznog stringa postavljanje placeholdera
        val listaGodina = listOf<String>("Godina","1","2","3","4","5")
        val listaIstrazivanja = listOf<String>("Istraživanje")
        val listaGrupa = listOf<String>("Grupa")
        spinnerGodina.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaGodina)
        spinnerIstrazivanje.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaIstrazivanja)
        spinnerGrupa.adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaGrupa)

        /*Spinner za Godine*/
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {
                dugmeIstrazivanje.isEnabled = false;
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(parent!!.getItemAtPosition(position).equals("Godina")){
                    dugmeIstrazivanje.isEnabled=false
                    user.trenutnaGodina=0
                }else {
                    user.trenutnaGodina = listaGodina[position].toInt()
                    dugmeIstrazivanje.isEnabled = false
                    odabirGodine = position
                    val listaIstrazivanja = mutableListOf<String>()
                    listaIstrazivanja.add("Istraživanje")
                    for (P in istrazivanjeViewModel.getIstrazivanjaByGodina(listaGodina[position].toInt())) {
                        listaIstrazivanja.add(P.naziv)
                    }
                    spinnerIstrazivanje.adapter = ArrayAdapter(this@UpisIstrazivanje,R.layout.simple_list_item_1, listaIstrazivanja)

                    /*Spinner za Istrazivanje*/
                    spinnerIstrazivanje.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            dugmeIstrazivanje.isEnabled = false;
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                            if(parent!!.getItemAtPosition(position).equals("Istraživanje")){
                                dugmeIstrazivanje.isEnabled=false
                            }else{
                                dugmeIstrazivanje.isEnabled=false
                                odabirIstrazivanja=position
                                val listaGrupa = mutableListOf<String>()
                                listaGrupa.add("Grupa")
                                for(P in grupaViewModel.getGroupsByIstrazivanje(listaIstrazivanja[position])){
                                    listaGrupa.add(P.naziv)
                                }
                                spinnerGrupa.adapter = ArrayAdapter(this@UpisIstrazivanje,R.layout.simple_list_item_1, listaGrupa)

                                /*Spinner za Grupu*/
                                spinnerGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                                    override fun onNothingSelected(parent: AdapterView<*>?) {
                                        dugmeIstrazivanje.isEnabled = false;
                                    }

                                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                        if(parent!!.getItemAtPosition(position).equals("Grupa")){
                                            dugmeIstrazivanje.isEnabled=false
                                        }else{
                                            odabirGrupe=position
                                            dugmeIstrazivanje.isEnabled=true
                                            dugmeIstrazivanje.setOnClickListener(){
                                                userViewModel.dodajUpisanoIstrazivanje(listaGodina[odabirGodine],
                                                    listaIstrazivanja[odabirIstrazivanja],
                                                    listaGrupa[odabirGrupe])
                                                val intent = Intent(this@UpisIstrazivanje, MainActivity::class.java)
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(user.trenutnaGodina!=0){
            spinnerGodina.setSelection(user.trenutnaGodina)
        }
    }
}