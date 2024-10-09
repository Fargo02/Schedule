package com.example.schedule.ui.home.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.R
import com.example.schedule.databinding.FragmentHomeBinding
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.ui.home.RouteAdapter
import com.example.schedule.ui.home.SearchState
import com.example.schedule.ui.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar


class HomeFragment: Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModel<HomeViewModel>()

    private var routes = ArrayList<StationInfo>()

    private val routesAdapter = RouteAdapter()

    private lateinit var datePickerDialog: DatePickerDialog

    private var date: String = ""
    private var fromCode: String = ""
    private var toCode: String = ""
    private  var transportType: String = ""

    private lateinit var textWatcherFrom: TextWatcher
    private lateinit var textWatcherTo: TextWatcher



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        routesAdapter.routes = routes
        binding.routeList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.routeList.adapter = routesAdapter

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.observeStationCodeListener().observe(viewLifecycleOwner) {
            Log.i("testRequest", "from = ${it.first.yandexCode}, to = ${it.second.yandexCode}, date = $date, type = $transportType")
            viewModel.requestToServer(it.first.yandexCode ?: "", it.second.yandexCode ?: "", date, transportType)
        }

        binding.compareArrows.setOnClickListener {
            val temp = toCode
            toCode = fromCode
            fromCode = temp

            binding.editTextFrom.removeTextChangedListener(textWatcherFrom)
            binding.editTextFrom.setText(fromCode)
            binding.editTextFrom.addTextChangedListener(textWatcherFrom)

            binding.editTextTo.removeTextChangedListener(textWatcherTo)
            binding.editTextTo.setText(toCode)
            binding.editTextTo.addTextChangedListener(textWatcherTo)
        }

        binding.btnToday.setOnClickListener {
            date = LocalDate.now().toString()
            binding.btnDate.text = getString(R.string.date)
        }
        binding.btnTomorrow.setOnClickListener {
            val today = LocalDate.now()
            date = today.plus(1, ChronoUnit.DAYS).toString()
            binding.btnDate.text = getString(R.string.date)
        }
        binding.btnDate.setOnClickListener {
            showCalendar()
        }
        binding.btnAny.setOnClickListener { transportType = "" }
        binding.btnPlane.setOnClickListener{ transportType = "Plane" }
        binding.btnTrain.setOnClickListener{ transportType = "Train" }
        binding.btnSuburban.setOnClickListener{ transportType = "Suburban" }
        binding.btnBus.setOnClickListener{ transportType = "Bus" }

        binding.btnFound.setOnClickListener {
            if (date.isEmpty() || transportType.isEmpty()) {
                binding.nothingFound.isVisible = true
                binding.nothingFound.text = "Указаны не все данные"
            } else {
                binding.nothingFound.isVisible = false
                viewModel.getCode(fromCode, toCode)
            }
        }
        //toCode = s9879631

        //fromCode = s9600771


        textWatcherFrom = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fromCode = s.toString()
            }

            override fun afterTextChanged(s: Editable?) { }
        }
        textWatcherFrom.let { binding.editTextFrom.addTextChangedListener(it) }

        textWatcherTo = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toCode = s.toString()
            }

            override fun afterTextChanged(s: Editable?) { }
        }
        textWatcherTo.let { binding.editTextTo.addTextChangedListener(it) }

    }


    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Loading -> showLoading()
            is SearchState.Content -> showContent(state.stationAndData)
            is SearchState.Error -> showError()
            is SearchState.Empty -> showEmpty()
        }
    }

    private fun showLoading() {
        binding.schedule.isVisible = false
        binding.listGroup.isVisible = false
        binding.progressBar.isVisible = true
        binding.routeList.isVisible = false
        binding.nothingFound.isVisible = false
    }

    private fun showContent(routes: List<StationInfo>) {
        binding.listGroup.isVisible = true
        binding.progressBar.isVisible = false
        binding.routeList.isVisible = true
        routesAdapter.routes.clear()
        routesAdapter.routes.addAll(routes)
        routesAdapter.notifyDataSetChanged()
    }

    private fun showError() {
        Log.e("RequestError", "Какая-то ошибка")
    }

    private fun showEmpty() {
        binding.listGroup.isVisible = true
        binding.progressBar.isVisible = false
        binding.nothingFound.isVisible = true
    }

    private fun showCalendar() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        datePickerDialog = DatePickerDialog(
            requireContext(), this , year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = ("$year" + "-" + (month + 1)) + "-" + dayOfMonth
        binding.btnDate.text = date
    }
}