import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("InjectDispatcher")
class KTestDispatchersImpl : KDispatchers {

    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()
}
