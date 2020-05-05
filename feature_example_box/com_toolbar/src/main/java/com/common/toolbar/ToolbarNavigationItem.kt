package com.common.toolbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

enum class ToolbarNavigationItem(@StringRes val description: Int, @DrawableRes val iconId: Int, val action: (Fragment?) -> Unit) {

    None(0, 0, {}),
    Back(R.string.menu_nav_item_back, R.drawable.ic_arrow_back_black_24dp, { it?.activity?.onBackPressed() }),
    Close(R.string.menu_nav_item_close, R.drawable.ic_close_black_24dp, { it?.activity?.onBackPressed() });
}
