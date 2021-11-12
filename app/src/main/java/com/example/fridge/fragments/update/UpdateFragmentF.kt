package com.example.fridge.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fridge.R
import com.example.fridge.modelF.ReceptedF
import com.example.fridge.viewmodel.receptedViewModel
import kotlinx.android.synthetic.main.fragment_update_fragment.*
import kotlinx.android.synthetic.main.fragment_update_fragment.view.*


class UpdateFragmentF : Fragment() {

    private val args by navArgs<UpdateFragmentFArgs>()

    private lateinit var mReceptedViewModel: receptedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_fragment, container, false)

        mReceptedViewModel = ViewModelProvider(this).get(receptedViewModel::class.java)

        view.updateFirstName_ed.setText(args.currentRecepted.Name)
        view.updateOpN_ed.setText(args.currentRecepted.Opisanie)
        view.updateText_ed.setText(args.currentRecepted.Text)

        view.update_button.setOnClickListener{
            updateItem()
        }

        //Внесение меню
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstName = updateFirstName_ed.text.toString()
        val OpisanieED = updateOpN_ed.text.toString()
        val TextEd = updateText_ed.text.toString()
        val updColor = updateColor.text.toString()

        if(inputCheck(firstName, OpisanieED, TextEd, updColor)){
            //Создание рецепт-объекта
            val updatedRecepted = ReceptedF(args.currentRecepted.id, firstName, OpisanieED, TextEd, updColor)
            //Обновление внесенных данных
            mReceptedViewModel.updateRecepted(updatedRecepted)
            Toast.makeText(requireContext(), "Запись обновлена.", Toast.LENGTH_SHORT).show()
            //возврат назад
            findNavController().navigate(R.id.action_updateFragmentF_to_listFragmentF)
        }
        else{
            Toast.makeText(requireContext(),"Пожалуйста, заполните строку заголовка.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, OpN: String, edtText: String, edtColorText: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(OpN) && TextUtils.isEmpty(edtText) && TextUtils.isEmpty(edtColorText))
    }

    //Создание кнопки удаления
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    //Выбор рецепта на удаление
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteRecepted()
        }
        return super.onOptionsItemSelected(item)
    }

    //Реализация удаления рецепта
    private fun deleteRecepted(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){_,_->
            mReceptedViewModel.deleteRecepted(args.currentRecepted)
            Toast.makeText(requireContext(), "Успешно удалено: ${args.currentRecepted.Name}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragmentF_to_listFragmentF)
        }
        builder.setNegativeButton("Нет"){_,_->}
        builder.setTitle("Удалить ${args.currentRecepted.Name}?")
        builder.setMessage("Вы точно хотите удалить ${args.currentRecepted.Name}?")
        builder.create().show()
    }

}