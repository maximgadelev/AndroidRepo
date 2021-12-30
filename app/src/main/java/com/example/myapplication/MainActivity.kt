package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.database.DataBase
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var todoBase: DataBase
private var scope= CoroutineScope(SupervisorJob()+Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoBase = DataBaseCreator().initDB(this)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TodoListFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu, menu)
        return true
    }



































    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.b_delete_all) {
            scope.launch {
                if (todoBase.todoDao().getAll().isNotEmpty()) {
                    AlertDialog.Builder(this@MainActivity)
                        .setMessage("Вы уверены?")
                        .setPositiveButton("Да") { dialog, _ ->
                            scope.launch {
                                todoBase.todoDao().deleteAll()
                                initFragment()
                                dialog.dismiss()
                            }
                        }
                        .setNegativeButton("Нет") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                } else Snackbar.make(binding.root, "У вас нет тудушек для удаления", 2000).show()
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
