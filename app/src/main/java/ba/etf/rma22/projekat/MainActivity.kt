package ba.etf.rma22.projekat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.view.FragmentAnkete
import ba.etf.rma22.projekat.view.FragmentIstrazivanje
import ba.etf.rma22.projekat.view.ViewPagerAdapter
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    companion object {

        public lateinit var viewPager: ViewPager2
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val grupaViewModel = IstrazivanjeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AccountRepository.setContext(applicationContext)
        AnketaRepository.setContext(applicationContext)
        IstrazivanjeIGrupaRepository.setContext(applicationContext)
        OdgovorRepository.setContext(applicationContext)
        PitanjeAnketaRepository.setContext(applicationContext)
        TakeAnketaRepository.setContext(applicationContext)

        viewPager = findViewById(R.id.pager)

        val fragments = mutableListOf<Fragment>()

        //viewPager.offscreenPageLimit = 2
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter

        fragments.add(0,FragmentAnkete.newInstance(viewPagerAdapter))
        fragments.add(1,FragmentIstrazivanje.newInstance(viewPagerAdapter))

        var viewPagerChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position==0 && fragments.size==2){
                    viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje.newInstance(viewPagerAdapter))
                }
            }
        }
        viewPager.registerOnPageChangeCallback(viewPagerChangeCallback)

        val payload = intent?.getStringExtra("payload")
        if (payload != null) {
            grupaViewModel.promijeniHash(payload, onSuccess = ::onSuccess, onError = ::onError)
        }
        else grupaViewModel.promijeniHash("2d5ceafb-0fd2-4282-b767-0a9740cd2c20",
            onSuccess = ::onSuccess, onError = ::onError)
    }
    fun onSuccess(){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val toast = Toast.makeText(applicationContext, "OK!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
    fun onError() {
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val toast = Toast.makeText(applicationContext, "ERROR!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}