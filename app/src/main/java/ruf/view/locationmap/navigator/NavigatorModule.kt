package ruf.view.locationmap.navigator

import android.os.Bundle
import android.support.annotation.IdRes
import ruf.view.locationmap.sample.ParentNavigator
import toothpick.Scope
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind
import toothpick.ktp.extension.getInstance
import java.util.*
import javax.inject.Provider

private const val UUID_KEY = "UUID_KEY"

class NavigatorModule(
    @IdRes containerId: Int,
    override val scopeName: String
) : ScopeModule() {


    //    override val scopeName: UUID = arguments?.getSerializable(UUID_KEY) as? UUID
//        ?: UUID.randomUUID().also { arguments?.putSerializable(UUID_KEY, it) }

    init {
        val navigator = Navigator(containerId, scopeName)
        bind<INavigator>().toInstance(navigator)
        bind<INavigatorCommand>().withName(ParentNavigator::class).toInstance(navigator)
    }

    override fun Scope.openSubScopes(): Scope = openSubScope(
        ScopeIdentifier(
            NavigatorModule::class,
            scopeName
        )
    )

    companion object {
        fun Any.injectNavigator(arguments: Bundle?) {
            val scopeName: Any = arguments?.getSerializable(UUID_KEY) as? UUID
                ?: error("This arguments haven't scopeName for navigator")
            injectScope<NavigatorModule>(scopeName)
        }
    }
}

abstract class NavigatorProvider(@IdRes val containerId: Int, val scopeName: String = UUID.randomUUID().toString()) : Provider<INavigator> {

    init {
        NavigatorModule(containerId, scopeName).takeUnless { it.isOpen() }?.installModule()
    }

    final override fun get() =
        KTP.openScope(ScopeModule.ScopeIdentifier(NavigatorModule::class, scopeName))
            .getInstance<INavigator>()
}

class InjectProvider(val nnn: NNN) : Provider<INavigator> {
    override fun get() =
        KTP.openScope(ScopeModule.ScopeIdentifier(NavigatorModule::class, nnn.scopeName))
            .getInstance<INavigator>()
}

enum class NNN(val scopeName: String) {
    Root("dsa"),
    List("asds")
}