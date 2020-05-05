package com.common.toolbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import ruf.view.core.actions.IAddItemListener
import ruf.view.core.actions.ISearchItemListener

enum class ToolbarItem(@StringRes val title: Int, @DrawableRes val iconId: Int, val action: (Fragment?) -> Boolean) {

    Add(R.string.menu_item_add, R.drawable.ic_add_black_24dp, { (it as? IAddItemListener)?.onClickItemAdd() != null }),
    Search(R.string.menu_item_search, R.drawable.ic_search_black_24dp, { (it as? ISearchItemListener)?.onClickItemSearch() != null })
}
