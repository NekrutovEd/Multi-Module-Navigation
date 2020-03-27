package ruf.view.core

import android.os.Parcelable
import kotlin.reflect.KClass

interface RouterClass<out T : Any> : Parcelable {
    val kClass: KClass<out T>
}