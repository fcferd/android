package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import fragments.AboutCPCFragment
import fragments.BookListFragment
import fragments.LibraryServicesOrganizationalChartFragment

 class TabPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BookListFragment()
            1 -> LibraryServicesOrganizationalChartFragment()
            2 -> AboutCPCFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navView)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                // Handle navigation drawer item clicks here
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BookListFragment())
            .commit()

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = TabPagerAdapter(this)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Book List"
                1 -> "Library Services Organizational Chart"
                2 -> "About Cpc"
                else -> null
            }
        }.attach()
    }
}
