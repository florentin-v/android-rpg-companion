import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.KoinApplication
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension
import org.koin.test.junit5.mock.MockProviderExtension
import org.mockito.Mockito.mock

abstract class BasicKoinTest : KoinTest {

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        buildModules()
    }

    abstract fun KoinApplication.buildModules()

    @JvmField
    @RegisterExtension
    val mockProvider = MockProviderExtension.create { clazz ->
        mock(clazz.java)
    }
}
