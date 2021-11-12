package com.example.fridge.fragments.add

import android.content.Context
import android.content.Intent.getIntent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fridge.R
import com.example.fridge.fragments.list.ListAdapter
import com.example.fridge.modelF.ReceptedF
import com.example.fridge.viewmodel.receptedViewModel
import kotlinx.android.synthetic.main.custom_row_fr.*
import kotlinx.android.synthetic.main.fragment_add_fragment.*
import kotlinx.android.synthetic.main.fragment_add_fragment.view.*


class addFragmentF : Fragment() {

    private lateinit var mUserViewModel: receptedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_fragment, container, false)

        mUserViewModel = ViewModelProvider(this).get(receptedViewModel::class.java)

        view.colorTxt.setText("0")
        view.boxSF.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked){
                view.colorTxt.setText("")
                view.colorTxt.setText("Color1")
            } else {
                view.colorTxt.setText("")
                view.colorTxt.setText("0")
            }
        }


        view.add_button.setOnClickListener {
            insertDataToDataBase()
        }

        return view
    }

    private fun insertDataToDataBase() {
        val firstName = addFirstName_ed.text.toString()
        val OpN = addOpN_ed.text.toString()
        val edtTxt = editText_ed.text.toString()
        val edtColorText = colorTxt.text.toString()

        if (inputCheck(firstName, OpN, edtTxt, edtColorText)){
            //Создание записи
            val RcptF =
                ReceptedF(0, firstName, OpN, edtTxt, edtColorText)

            //Добавление записи в БД
            mUserViewModel.addRecepted(RcptF)
            Toast.makeText(requireContext(),"Рецепт добавлен!", Toast.LENGTH_LONG).show()


            findNavController().navigate(R.id.action_addFragmentF_to_listFragmentF2)
        }
        else{
            Toast.makeText(requireContext(), "Пожалуйста, заполните строку заголовка.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, OpN: String, edtText: String, edtColorText: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(OpN) && TextUtils.isEmpty(edtText) && TextUtils.isEmpty(edtColorText))
    }


}