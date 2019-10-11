package com.github.alaytsev.yandexmapdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.mapkit.MapKitFactory
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment private constructor() : Fragment() {


    private val dataSet by lazy { IntRange(0, 20).map { it.toString() } }
    private val adapter by lazy { Adapter(dataSet) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("PASTE HERE YOUR MAPKIT KEY")
        MapKitFactory.initialize(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_main, container, false)

    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStart()
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            overlapView.isVisible = !overlapView.isVisible
        }

        recyclerView.apply {
            adapter = this@MainFragment.adapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }


    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView by lazy { checkNotNull(view.findViewById<TextView>(R.id.itemText)) }
    }

    private class Adapter(private val dataSet: List<String>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return dataSet.count()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = dataSet[position]
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}