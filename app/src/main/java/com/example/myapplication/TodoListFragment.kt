package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.database.DataBase
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.*
import kotlin.collections.ArrayList
import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentCreateTodoBinding
import com.example.myapplication.entity.Todo
import com.example.myapplication.databinding.FragmentTodoListBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.*
import java.text.SimpleDateFormat


class TodoListFragment: Fragment() {
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var todoDataBASE: DataBase
    private var scope= CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private lateinit var todos:ArrayList<Todo>
    private lateinit var locationClient: FusedLocationProviderClient
    private var calendar: Calendar? = null
    private var changingLongitude: Double? = null
    private var changingLatitude: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoDataBASE = (requireActivity() as MainActivity).todoBase
        scope.launch {
            initRecyclerView()
        }
        binding.bAddTodo.setOnClickListener {
            showEditOrAdd(null, 0)
        }
    }

    private suspend fun initRecyclerView() {
        todos = withContext(scope.coroutineContext) {
            todoDataBASE.todoDao().getAll() as ArrayList<Todo>
        }
            val decorator = DividerItemDecoration(requireContext(),RecyclerView.VERTICAL)
            with(binding.rvTodos) {
                layoutManager = LinearLayoutManager(context).apply {
                    orientation = RecyclerView.VERTICAL
                }
                addItemDecoration(decorator)
                adapter = TodoAdapter(todos).apply {
                    infoClickListener = {
                        showEditOrAdd(it,1)
                    }
                    deleteClickListener = {
                        scope.launch {
                            todoDataBASE.todoDao().deleteTodo(it.id)
                            initRecyclerView()
                        }
                    }
                    submitList(todos)
                }
            }
            initNoTodoList()
        }


    private fun showDatePicker(bindingOfEditScreen: FragmentCreateTodoBinding) {
        calendar = Calendar.getInstance()
        calendar?.let { calendar ->
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, day)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
        showTimePicker(bindingOfEditScreen)
    }

    private fun showTimePicker(bindingOfEditScreen: FragmentCreateTodoBinding) {
        val datePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Выберите время для задачи")
            .build()
        with(datePicker) {
            show(this@TodoListFragment.childFragmentManager, "123")
            addOnPositiveButtonClickListener {
                calendar?.let {
                    it[Calendar.HOUR_OF_DAY] = datePicker.hour
                    it[Calendar.MINUTE] = datePicker.minute
                    it[Calendar.SECOND] = 0
                    it[Calendar.MILLISECOND] = 0
                    bindingOfEditScreen.mbSetupTime.text = convertToTime(calendar?.time?.time)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            100 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupLocation()
                } else {
                    Toast.makeText(context, "Доступ к локации запрещён", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun convertToTime(time: Long?): String {
        time?.let {
            val date = Date(it)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("dd.MM.yyyy HH:mm").format(date)
            } else ""
        }
        return ""
    }


    private fun initNoTodoList() {
        with(binding) {
            scope.launch {
                ivNoTodo.visibility =
                    if (todoDataBASE.todoDao().getAll().isNotEmpty()) View.GONE else View.VISIBLE
                tvNoTodo.visibility =
                    if (todoDataBASE.todoDao().getAll().isNotEmpty()) View.GONE else View.VISIBLE
            }
        }
    }


    private fun showEditOrAdd(todo: Todo?, editOrAdd: Int) {
        val bindingOfEditScreen = FragmentCreateTodoBinding.inflate(LayoutInflater.from(context))
        var needToChangeDate = false
        val alert = context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(if (editOrAdd == 1) "Редактировать" else "Создать")
                setView(bindingOfEditScreen.root)
            }.show()
        }
        when (editOrAdd) {
            1 -> {
                with(bindingOfEditScreen) {
                    tvEnterTitle.text = SpannableStringBuilder(todo?.title)
                    teEnterDescription.text = SpannableStringBuilder(todo?.description)
                    changingLongitude = todo?.longitude
                    changingLatitude = todo?.latitude
                }
            }
        }
        bindingOfEditScreen.mbSetupTime.setOnClickListener {
            showDatePicker(bindingOfEditScreen)
            needToChangeDate = true
        }
        bindingOfEditScreen.mbSetupLocation.setOnClickListener {
            setupLocation()
        }
        bindingOfEditScreen.bOk.setOnClickListener {
            when (editOrAdd) {
                1 -> {
                    with(todoDataBASE.todoDao()) {
                        todo?.id?.let { it1 ->
                            scope.launch {
                                updateTitle(it1, bindingOfEditScreen.tvEnterTitle.text.toString())
                                updateDescription(
                                    it1,
                                    bindingOfEditScreen.teEnterDescription.text.toString()
                                )
                                updateDate(it1, calendar?.time)
                                updateLongitude(it1, changingLongitude)
                                updateLatitude(it1, changingLatitude)
                            }
                        }
                    }
                }
                0 -> {
                    val newTodo = Todo(
                        0,
                        if(bindingOfEditScreen.tvEnterTitle.text.toString()!="") bindingOfEditScreen.tvEnterTitle.text.toString() else "Не указано",
                        if(bindingOfEditScreen.teEnterDescription.text.toString()!= "") bindingOfEditScreen.teEnterDescription.text.toString() else "Не указано",
                        if (needToChangeDate) calendar?.time else null,
                        changingLongitude,
                        changingLatitude
                    )
                    scope.launch {
                        todoDataBASE.todoDao().add(newTodo)
                    }
                }
            }
            needToChangeDate = false
            changingLatitude = null
            changingLongitude = null
            parentFragmentManager.beginTransaction().replace(R.id.container,TodoListFragment()).commit()
            alert?.dismiss()
        }
        bindingOfEditScreen.btnDismiss.setOnClickListener {
            alert?.dismiss()
        }
    }

    private fun setupLocation() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_DENIED) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
            requestPermissions(permissions, 100)
        } else {
            locationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            locationClient.lastLocation.addOnSuccessListener { location: Location? ->
                changingLatitude = location?.latitude
                changingLongitude = location?.longitude
                Toast.makeText(context,
                    if (location != null) "Локация обнаружена" else "Локация не обнаружена. Включите геолокацию и перезайдите",
                    if (location != null) Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
