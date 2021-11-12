package com.example.fridge.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.fridge.MyProducts.ProductsFAdapter
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.MyProducts.ProductsGr
import com.example.fridge.R
import com.example.fridge.modelF.ProductsF
import com.example.fridge.viewmodel.productsViewModel
import kotlinx.android.synthetic.main.fragment_add_fragmentfor_products.*
import kotlinx.android.synthetic.main.fragment_add_fragmentfor_products.view.*
import java.util.*
import kotlin.collections.ArrayList


class addFragmentforProducts : androidx.fragment.app.DialogFragment(), DatePickerDialog.OnDateSetListener, AdapterView.OnItemClickListener {

    private var gridView: GridView? = null
    private var arrayList: ArrayList<ProductsGr>? = null
    private var productsAdapter: ProductsFAdapter? = null

    private lateinit var mProductViewModel: productsViewModel

    var day = 0
    var month = 0
    var year = 0

    var countDays1 = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    var countDayP = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_fragmentfor_products, container, false)

        mProductViewModel = ViewModelProvider(this).get(productsViewModel::class.java)

        gridView = view.findViewById(R.id.grProducts)
        arrayList = ArrayList()
        arrayList = setDataList()
        productsAdapter = ProductsFAdapter(requireActivity().applicationContext, arrayList!!)
        gridView?.adapter = productsAdapter
        gridView?.onItemClickListener = this

        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

        view.DataTextProd.text = "$day-$month-$year"

        view.addDateButton.setOnClickListener {
             getDateTimeCalendar()
             DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        val bundle = Bundle()
        val fragmentManager = (context as ProductsFOpen).supportFragmentManager

        val edTP: EditText = view.findViewById(R.id.add_editTextProductName)
        var firstNameProductsGr = edTP.text.toString()

        view.SaveIdProd.setOnClickListener {
            insertDataToDataBaseP()
            savedDay += countDays1
            bundle.putString("data", savedDay.toString())
            fragmentManager.setFragmentResult("dataFromAdapter", bundle)
        }

        return view
    }

    private fun setDataList(): ArrayList<ProductsGr> {
        var arrayList: ArrayList<ProductsGr> = ArrayList()

        arrayList.add(ProductsGr("Яблоко", R.drawable.apple)) //0
        arrayList.add(ProductsGr("Банан", R.drawable.banana)) //1
        arrayList.add(ProductsGr("Ананас", R.drawable.pineapple)) //2
        arrayList.add(ProductsGr("Апельсин", R.drawable.orange)) //3
        arrayList.add(ProductsGr("Арбуз", R.drawable.watermelon)) //4
        arrayList.add(ProductsGr("Лук", R.drawable.onion)) //5
        arrayList.add(ProductsGr("Баклажан", R.drawable.eggplant)) //6
        arrayList.add(ProductsGr("Яйцо", R.drawable.egg)) //7
        arrayList.add(ProductsGr("Морковь", R.drawable.carrot)) //8
        arrayList.add(ProductsGr("Томат", R.drawable.tomate)) //9
        arrayList.add(ProductsGr("Молоко", R.drawable.milk2)) //10
        arrayList.add(ProductsGr("Чеснок", R.drawable.garlic)) //11
        arrayList.add(ProductsGr("Рыба", R.drawable.fish)) //12
        arrayList.add(ProductsGr("Консервы", R.drawable.cannedfood)) //13

        return arrayList
    }

    private fun insertDataToDataBaseP() {
        val firstNameProduct = add_editTextProductName.text.toString()
        val numberProduct = editTextNumber.text
        val dateProducts = DataTextProd.text.toString()
        val countDays = editTextNumber2.text
        val countDays2 = Integer.parseInt(countDays.toString())
        countDays1 += countDays2

        if (inputCheck(firstNameProduct, numberProduct, countDays)) {
            //Создание записи
            val prodF = ProductsF(0, firstNameProduct, Integer.parseInt(numberProduct.toString()), dateProducts, Integer.parseInt(countDays.toString()))
            //Добавление записи в БД
            mProductViewModel.addProducts(prodF)
            Toast.makeText(requireContext(), "Продукт добавлен!", Toast.LENGTH_LONG).show()
            //Возврат
            dismiss()
            //  findNavController().navigate(R.id.action_addFragmentforProducts_to_listFragmentForProductsF)
        } else {
            Toast.makeText(
                requireContext(),
                "Пожалуйста, добавьте название продукта.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val TAG = "addDialog"
    }

    private fun inputCheck(firstNameProduct: String, numberProduct: Editable, countDays: Editable): Boolean {
        return !(TextUtils.isEmpty(firstNameProduct) && numberProduct.isEmpty() && countDays.isEmpty())
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var productsItem: ProductsGr = arrayList!!.get(position)
        //Toast.makeText(requireActivity().applicationContext, "Вы выбрали: " + productsItem.name, Toast.LENGTH_SHORT).show()

        val edT: EditText = requireView().findViewById(R.id.add_editTextProductName)
        when(position){
            0 -> {
                edT.text.clear()
                edT.setText("Яблоко")
            }
            1 -> {
                edT.text.clear()
                edT.setText("Банан")
            }
            2 -> {
                edT.text.clear()
                edT.setText("Ананас")
            }
            3 -> {
                edT.text.clear()
                edT.setText("Апельсин")
            }
            4 -> {
                edT.text.clear()
                edT.setText("Арбуз")
            }
            5 -> {
                edT.text.clear()
                edT.setText("Лук")
            }
            6 -> {
                edT.text.clear()
                edT.setText("Баклажан")
            }
            7 -> {
                edT.text.clear()
                edT.setText("Яйцо")
            }
            8 -> {
                edT.text.clear()
                edT.setText("Морковь")
            }
            9 -> {
                edT.text.clear()
                edT.setText("Томат")
            }
            10 -> {
                edT.text.clear()
                edT.setText("Молоко")
            }
            11 -> {
                edT.text.clear()
                edT.setText("Чеснок")
            }
            12 -> {
                edT.text.clear()
                edT.setText("Рыба")
            }
            13 -> {
                edT.text.clear()
                edT.setText("Консервы")
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            savedDay = dayOfMonth
            savedMonth = month
            savedYear = year



        getDateTimeCalendar()
        DataTextProd.text = "$savedDay-$savedMonth-$savedYear"
//        countDayP = savedDay.minus(day)
//        test1.setText(countDayP)
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }
}