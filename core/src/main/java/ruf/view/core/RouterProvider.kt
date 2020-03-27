package ruf.view.core

import android.os.Parcelable
import javax.inject.Provider
import kotlin.reflect.KClass

interface RouterClassProvider<T : Any> : Provider<KClass<out T>>, Parcelable