package com.example.fridge.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridge.R
import com.example.fridge.databinding.ActivityMainBinding
import com.example.fridge.fragments.update.UpdateFragmentFArgs
import com.example.fridge.viewmodel.receptedViewModel
import kotlinx.android.synthetic.main.fragment_list_fragment.view.*
import android.view.MenuInflater as MenuInflater1

class listFragmentF : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    // FragmentComponent, and ViewComponent

    private lateinit var binding: ActivityMainBinding

    private val args by navArgs<UpdateFragmentFArgs>()

    private val mainViewModel: receptedViewModel by viewModels()

    private val adapter: ListAdapter by lazy{ListAdapter(mReceptedViewModel, requireContext())}

    private lateinit var mReceptedViewModel: receptedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_fragment, container, false)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //RecyclerView
        val recyclerView = view.recyclerview

        //ReceptedViewModel
        mReceptedViewModel = ViewModelProvider(this).get(receptedViewModel::class.java)
        mReceptedViewModel.readAllData.observe(viewLifecycleOwner, Observer { Name ->
            //val adapter = ListAdapter(mReceptedViewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter.setData(Name) })

        view.floatingActionButton2.setOnClickListener{
                findNavController().navigate(R.id.action_listFragmentF_to_addFragmentF2)
        }
        //Add menu
        setHasOptionsMenu(true)

        return view
    }

    //Добавление кнопки удаления в лист рецептов
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater1) {
        inflater.inflate(R.menu.main_menufs, menu)
        inflater.inflate(R.menu.delete_menu, menu)
        val search = menu?.findItem(R.id.main_search)
        val searchView = search.actionView as? androidx.appcompat.widget.SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu, inflater)

    }
    //Реализация работы кнопки удаления при нажатии
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllRecepted()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllRecepted() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){_,_->
            mReceptedViewModel.deleteAllRecepted()
            Toast.makeText(requireContext(), "Успешно удалены все заметки", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Нет"){_,_->}
        builder.setTitle("Удалить все заметки?")
        builder.setMessage("Вы точно хотите удалить все заметки?")
        builder.create().show()
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"

        mainViewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let{
                adapter.setData(it)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searchDatabase(query)
        }
        return true
    }

}


