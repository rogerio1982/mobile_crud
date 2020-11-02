package com.example.myapplication

import DataBaseHandler
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //setSupportActionBar(findViewById(R.id.toolbar))



        val context = this
        val db = DataBaseHandler(context)
        btnInsert.setOnClickListener {
            if (editTextName.text.toString().isNotEmpty() &&
                editTextAge.text.toString().isNotEmpty()
            ) {
               // val user = User()
                val user = User(editTextName.text.toString(), editTextAge.text.toString())
                db.insertData(user)
                clearField()
                } else {
                Toast.makeText(context, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        }

        btnRead.setOnClickListener {
            val data = db.readDat()
            tvResult.text = ""
            for (i in 0 until data.size) {
                tvResult.append(
                    data[i].name + " " + data[i].age + "\n"
                )
            }
        }
    }




    private fun clearField() {
        editTextName.text.clear()
        editTextAge.text.clear()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}