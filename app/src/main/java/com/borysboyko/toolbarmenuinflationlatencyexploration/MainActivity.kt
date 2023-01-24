package com.borysboyko.toolbarmenuinflationlatencyexploration

import android.os.Bundle
import android.os.Trace
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.borysboyko.toolbarmenuinflationlatencyexploration.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }
    }

    var initialInflation = -1L

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Trace.beginSection("onCreateOptionsMenu (Activity-owned App Bar)")
        val start = System.currentTimeMillis()
        menuInflater.inflate(R.menu.menu_main, menu)
        initialInflation = (System.currentTimeMillis() - start)
        Log.e("BORYS", "onCreateOptionsMenu (Activity-owned App Bar) took: " + initialInflation + "ms to inflate 12 menu items")
        Trace.endSection()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_menu_item_1 -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
//
//    var addTime = -1L
//
//    fun addToolbarMenuItems(toolbar: MaterialToolbar?, toActivity: Boolean): Long {
//        var targetToolbar = toolbar
//        var activityOrFragment = "Fragment"
//        if (toActivity) {
//            targetToolbar = binding.toolbar
//            activityOrFragment = "Activity"
//            Trace.beginSection("toolbar.menu.add - $activityOrFragment")
//        } else {
//            Trace.beginSection("toolbar.menu.add - $activityOrFragment")
//        }
//
//        val start = System.currentTimeMillis()
//        targetToolbar!!.menu.add("New menu item 1");
//        targetToolbar.menu.getItem(0).setIcon(R.drawable.baseline_playlist_add_check_24)
//        targetToolbar.menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
//        targetToolbar.menu.add("New menu item 2");
//        targetToolbar.menu.add("New menu item 3");
//        targetToolbar.menu.add("New menu item 4");
//        targetToolbar.menu.add("New menu item 5");
//        targetToolbar.menu.add("New menu item 6");
//        addTime = (System.currentTimeMillis() - start)
//
//
//        Log.e("BORYS", "toolbar.menu.add took: " + addTime + "ms to add 6 menu items for $activityOrFragment")
//        Trace.endSection()
//
//        return addTime
//    }
//
//    var clearTime = -1L
//
//    fun clearAllToolbarMenuItems(toolbar: MaterialToolbar?, toActivity: Boolean): Long {
//        var targetToolbar = toolbar
//        var activityOrFragment = "Fragment"
//        if (toActivity) {
//            targetToolbar = binding.toolbar
//            activityOrFragment = "Activity"
//            Trace.beginSection("toolbar.menu.clear - $activityOrFragment")
//        } else {
//            Trace.beginSection("toolbar.menu.clear - $activityOrFragment")
//        }
//
//        val start = System.currentTimeMillis()
//        targetToolbar!!.menu.clear()
//        clearTime = (System.currentTimeMillis() - start)
//
//        Log.e("BORYS", "toolbar.menu.clear took: " + clearTime + "ms to clear all menu items for $activityOrFragment")
//        Trace.endSection()
//
//        return clearTime
//    }
}