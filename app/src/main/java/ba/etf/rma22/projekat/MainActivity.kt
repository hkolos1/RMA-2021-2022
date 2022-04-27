package ba.etf.rma22.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.view.FragmentAnkete
import ba.etf.rma22.projekat.view.FragmentIstrazivanje
import ba.etf.rma22.projekat.view.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        public lateinit var viewPager: ViewPager2
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}