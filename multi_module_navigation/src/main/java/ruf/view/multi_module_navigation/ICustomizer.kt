package ruf.view.multi_module_navigation

import androidx.fragment.app.FragmentTransaction

interface ICustomizer {
    fun customize(fragmentTransaction: FragmentTransaction)
}