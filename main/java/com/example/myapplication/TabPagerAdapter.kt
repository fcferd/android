package com.example.myapplication
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import fragments.AboutCPCFragment
import fragments.BookListFragment
import fragments.LibraryServicesOrganizationalChartFragment


class TabPagerAdapter(fragmentActivity: FragmentActivity):
FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
     return when (position) {
         0 -> BookListFragment()
         1 -> AboutCPCFragment()
         2 -> LibraryServicesOrganizationalChartFragment()
         else -> throw IllegalStateException("Invalid position: $position")
     }
    }
    }