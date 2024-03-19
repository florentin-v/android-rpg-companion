
import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.testing.TestNavHostController
import com.fvanaldewereld.rpgcompanion.MainActivity
import org.junit.Rule

open class BaseTestSuite {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    open lateinit var navHostController: TestNavHostController
    open lateinit var context: Context

}
