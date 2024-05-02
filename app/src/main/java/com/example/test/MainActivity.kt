package com.example.test

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import com.example.test.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var button2: Button
    private lateinit var spinnerMain: Spinner
    private lateinit var spinnerMain2: Spinner
    private lateinit var textViewResult: TextView
    private lateinit var showPopupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner(binding.spinnerMain, arrayListOf("Adventure", "Comedy", "Romance", "Action", "Fantasy"))
        setupSpinner(binding.spinnerMain2, arrayListOf("Animals", "Royalty", "Superheroes", "Magic", "Villains"))

        button2 = binding.button2
        spinnerMain = binding.spinnerMain
        spinnerMain2 = binding.spinnerMain2
        textViewResult = binding.ResultText

        button2.setOnClickListener(this)

        // Initialize and set the popup button
        showPopupButton = findViewById(R.id.ShowPopUp)  // Make sure the ID matches your layout
        showPopupButton.setOnClickListener {
            createPopupWindow()  // Now calling here to show the popup
        }

    }

    private fun setupSpinner(spinner: Spinner, options: ArrayList<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun createPopupWindow() {
        // LayoutInflater to inflate the popup's layout
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popUpView = inflater.inflate(R.layout.mainpopup, null)  // Ensure this is the correct layout file

        // Define the width and height of the popup
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT  // or MATCH_PARENT if full screen needed

        // Create the PopupWindow instance
        val focusable = true  // Lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(popUpView, width, height, focusable)

        // Find the button within the popup layout and set its click listener
        val gotItButton: Button = popUpView.findViewById(R.id.GotIt)
        gotItButton.setOnClickListener {
            popupWindow.dismiss()  // Dismiss the popup when "Got it" button is clicked
        }

        // Display the popup window
        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)  // Using binding.root as location reference
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onClick(v: View?) {
        val genrePosition = spinnerMain.selectedItemPosition
        val themePosition = spinnerMain2.selectedItemPosition
        if (genrePosition == 0) { // Adventure
            when (themePosition) {
                0 -> textViewResult.text = "You should watch:\nThe Lion King"
                1 -> textViewResult.text = "You should watch:\nAladdin"
                2 -> textViewResult.text = "You should watch:\nBig Hero 6"
                3 -> textViewResult.text = "You should watch:\nAlice in Wonderland"
                4 -> textViewResult.text = "You should watch:\nPirates of the Caribbean"
            }
        } else if (genrePosition == 1) { // Comedy
            when (themePosition) {
                0 -> textViewResult.text = "You should watch:\nZootopia"
                1 -> textViewResult.text = "You should watch:\nThe Princess & the Frog"
                2 -> textViewResult.text = "You should watch:\nThe Incredibles"
                3 -> textViewResult.text = "You should watch:\nEnchanted"
                4 -> textViewResult.text = "You should watch:\nDespicable Me"
            }
        } else if (genrePosition == 2) { // Romance
            when (themePosition) {
                0 -> textViewResult.text = "You should watch:\nLady and the Tramp"
                1 -> textViewResult.text = "You should watch:\nCinderella"
                2 -> textViewResult.text = "You should watch:\nBeauty and the Beast (Live)"
                3 -> textViewResult.text = "You should watch:\nBeauty and the Beast"
                4 -> textViewResult.text = "You should watch:\nThe Little Mermaid"
            }
        } else if (genrePosition == 3) { // Action
            when (themePosition) {
                0 -> textViewResult.text = "You should watch:\nThe Jungle Book"
                1 -> textViewResult.text = "You should watch:\nMulan"
                2 -> textViewResult.text = "You should watch:\nThe Avengers"
                3 -> textViewResult.text = "You should watch:\nSorcerer's Apprentice"
                4 -> textViewResult.text = "You should watch:\n101 Dalmatians"
            }
        } else if (genrePosition == 4) { // Fantasy
            when (themePosition) {
                0 -> textViewResult.text = "You should watch:\nBambi"
                1 -> textViewResult.text = "You should watch:\nFrozen"
                2 -> textViewResult.text = "You should watch:\nThor: Ragnarok"
                3 -> textViewResult.text = "You should watch:\nFantasia"
                4 -> textViewResult.text = "You should watch:\nMaleficent"
            }
        }
    }

}

