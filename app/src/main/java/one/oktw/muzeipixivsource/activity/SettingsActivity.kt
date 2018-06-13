package one.oktw.muzeipixivsource.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import one.oktw.muzeipixivsource.R
import one.oktw.muzeipixivsource.activity.fragment.SettingsFragment
import one.oktw.muzeipixivsource.util.AppUtil.Companion.checkInstalled
import one.oktw.muzeipixivsource.util.AppUtil.Companion.viewMarket
import org.jetbrains.anko.browse

class SettingsActivity : AppCompatActivity() {
    companion object {
        private const val MUZEI_PACKAGE = "net.nurik.roman.muzei"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // if click update version notification
        intent.getStringExtra("new_version")?.let {
            browse(it)
            finish()
        }

        // check muzei installed
        if (!checkInstalled(this, MUZEI_PACKAGE)) {
            AlertDialog.Builder(this)
                .setMessage(R.string.muzei_not_install)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    startActivity(viewMarket(MUZEI_PACKAGE))
                }
                .setNegativeButton(android.R.string.no, null)
                .setCancelable(false)
                .show()
        }

        // Only create new fragment on first create activity
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
        }
    }
}
