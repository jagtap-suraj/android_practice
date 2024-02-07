import android.app.Application
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

class App : Application() {

    lateinit var boxStore: BoxStore
        private set

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(this@App).build()

        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(boxStore).start(this)
        }
    }
}
