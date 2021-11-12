package com.example.fridge.fragments.list

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridge.MyProducts.ProductsFOpen
import com.example.fridge.R
import com.example.fridge.fragments.add.addFragmentforProducts
import com.example.fridge.viewmodel.productsViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_list_for_products.view.*



class ListFragmentForProductsF: Fragment() {

    //notification
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    internal lateinit var img: ImageView

    var zm: ImageView? = null

   // private var myContext:  FragmentActivity? = null

    private lateinit var mProductViewModel: productsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_for_products, container, false)

        //RecyclerView
        val adapter = ListAdapterForProducts(requireContext())
        val recyclerView = view.recyclerview2
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //тестовые уведомления
        createNotificationChannel()

        //кнопка реализации (скрыта)
        view.addButton4.setOnClickListener {
                sendNotification()
            Toasty.success(requireContext(), "Тест пройден", Toast.LENGTH_SHORT, true).show();
//            val dialog = addFragmentforProducts()
//            dialog.setTargetFragment(this@ListFragmentForProductsF, 1)
//            fragmentManager?.let { it1 -> dialog.show(it1, "MyCustomDialog") }
        }

        //ProductsViewModel
        mProductViewModel = ViewModelProvider(this).get(productsViewModel::class.java)
        mProductViewModel.readAllDataP.observe(viewLifecycleOwner, Observer {product->
            adapter.setData(product)
        })

        (context as ProductsFOpen).supportFragmentManager.setFragmentResultListener("dataFromAdapter", this, object: FragmentResultListener{
            override fun onFragmentResult(requestKey: String, result: Bundle) {
                val data =result.getString("data").toString()
                Toasty.success(requireContext(), data, Toasty.LENGTH_SHORT, true).show()
            }

        })

        view.floatingActionButton.setOnClickListener{
         val dialog = addFragmentforProducts()
           dialog.setTargetFragment(this@ListFragmentForProductsF, 1)
           fragmentManager?.let { it1 -> dialog.show(it1, "MyCustomDialog") }
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllProducts()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllProducts() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){_,_->
            mProductViewModel.deleteAllProducts()
            Toast.makeText(requireContext(), "Все успешно удалено", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Нет"){_,_->}
        builder.setTitle("Удалить все продукты?")
        builder.setMessage("Вы действительно хотите удалить все продукты?")
        builder.create().show()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description=descriptionText
            }

            val notificationManager: NotificationManager = requireContext().applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val intent = Intent(requireActivity().applicationContext, ProductsFOpen::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivities(requireActivity().applicationContext, 0, arrayOf(intent), 0)

        val bitmap = BitmapFactory.decodeResource(requireContext().applicationContext.resources, R.drawable.apple)
        val bitmapLargeIcon = BitmapFactory.decodeResource(requireContext().applicationContext.resources, R.drawable.garlic)

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.apple)
            .setContentTitle("Тестовый заголовок")
            .setContentText("Тестовое описание")
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))//BigTextStyle().bigText("Тест ТестТестТестТестТестТестТест"))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext().applicationContext)){
            notify(notificationId, builder.build())
        }
    }


}