package com.example.fridge.fragments.update

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.R
import com.example.fridge.modelF.ProductsF
import com.example.fridge.viewmodel.productsViewModel
import kotlinx.android.synthetic.main.activity_products_f.*
import kotlinx.android.synthetic.main.activity_products_f.view.*
import kotlinx.android.synthetic.main.fragment_add_fragmentfor_products.*
import kotlinx.android.synthetic.main.fragment_add_fragmentfor_products.view.*
import kotlinx.android.synthetic.main.fragment_update_products.*
import kotlinx.android.synthetic.main.fragment_update_products.view.*
import java.util.*


class UpdateFragmentProducts : Fragment(), DatePickerDialog.OnDateSetListener {

    private val args by navArgs<UpdateFragmentProductsArgs>()

    private lateinit var mProductViewModel: productsViewModel

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_products, container, false)

        mProductViewModel = ViewModelProvider(this).get(productsViewModel::class.java)

        view.update_ProductName.setText(args.currentProduct.nameP)
        view.updateProductNumber.setText(args.currentProduct.countP.toString())
        view.UpdateDataTextProd.setText(args.currentProduct.dateP)
        view.updateTextNumber2.setText(args.currentProduct.countD.toString())

        (context as ProductsFOpen).toolbarR4.visibility = View.GONE

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_updateFragmentProducts_to_listFragmentForProductsF)
                (context as ProductsFOpen).toolbarR4.visibility = View.VISIBLE
            }
        }

        view.BackButton1.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragmentProducts_to_listFragmentForProductsF)
            (context as ProductsFOpen).toolbarR4.visibility = View.VISIBLE
        }

        view.DeleteButtonDate1.setOnClickListener {
            deleteProduct()

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        view.UpdateIdProd.setOnClickListener {
            updateItem()
        }

        view.UpdateButtonDate.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        setHasOptionsMenu(true)

        return view
    }



    private fun updateItem() {
        val nameP = update_ProductName.text.toString()
        val countP = Integer.parseInt(updateProductNumber.text.toString())
        val dateP = UpdateDataTextProd.text.toString()
        val countD1 = Integer.parseInt(updateTextNumber2.text.toString())

        if(inputCheck(nameP, updateProductNumber.text, dateP, updateTextNumber2.text)) {
            //Создание продукта
            val updatedProducts = ProductsF(args.currentProduct.id, nameP, countP, dateP, countD1)
            //ОБновление текущего продукта
            mProductViewModel.updateProducts(updatedProducts)
            Toast.makeText(requireContext(), "Обновлено.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragmentProducts_to_listFragmentForProductsF)
            (context as ProductsFOpen).toolbarR4.visibility = View.VISIBLE
        } else {
            Toast.makeText(requireContext(), "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstNameProduct: String, numberProduct: Editable, dateProducts: String,  countDays: Editable): Boolean {
        return !(TextUtils.isEmpty(firstNameProduct) && numberProduct.isEmpty() && TextUtils.isEmpty(dateProducts) && countDays.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteProduct()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteProduct() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){_,_->
            mProductViewModel.deleteProduct(args.currentProduct)
            Toast.makeText(requireContext(), "Успешно удалено: ${args.currentProduct.nameP}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragmentProducts_to_listFragmentForProductsF)
            (context as ProductsFOpen).toolbarR4.visibility = View.VISIBLE
        }
        builder.setNegativeButton("Нет"){_,_->}
        builder.setTitle("Удалить ${args.currentProduct.nameP}?")
        builder.setMessage("Вы действительно хотите удалить ${args.currentProduct.nameP}?")
        builder.create().show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        UpdateDataTextProd.text = "$savedDay-$savedMonth-$savedYear"
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }


}