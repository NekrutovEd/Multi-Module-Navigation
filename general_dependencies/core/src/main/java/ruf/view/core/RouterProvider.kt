package ruf.view.core

import android.os.Parcelable
import kotlin.reflect.KClass

interface IRouterClass<out T : IRouter> : Parcelable {

    val kClass: KClass<out T>
}