package com.yeinerdpajaro.nemohachi.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yeinerdpajaro.nemohachi.R

private val TAB_TITLES = arrayOf(
    R.string.anuncios,
    R.string.perdidos,
    R.string.adopcion

)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> return AnunciosFragment()
            1 -> return PerdidosFragment()
            else -> AdopcionFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}