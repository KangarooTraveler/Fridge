package com.example.fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.view.*

open class DialogFragmentF: DialogFragment() {

    open fun newInstance(): DialogFragmentF? {
        return DialogFragmentF()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_dialog, container, false)

        rootView.okIdD.setOnClickListener{
            dismiss()
        }

        return rootView
    }
}