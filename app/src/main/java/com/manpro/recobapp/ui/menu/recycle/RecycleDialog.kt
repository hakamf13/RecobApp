/*
package com.manpro.recobapp.ui.menu.recycle

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel

class RecycleDialog(
    private val vm: RecycleViewModel,
    private val items: List<RecycleModel>,
    private val activity: Activity
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_list_recycle, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val etJumlah = view.findViewById<EditText>(R.id.etNumberRecycle)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmitNumberRecycle)

        */
/*btnSubmit.setOnClickListener {
            val item = items.find { it.id == vm.selectedI }
        }*//*

    }
}*/
