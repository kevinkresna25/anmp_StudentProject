package com.ubaya.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.studentproject.R
import com.ubaya.studentproject.databinding.FragmentStudentListBinding
import com.ubaya.studentproject.databinding.StudentListItemBinding
import com.ubaya.studentproject.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    // ngeload layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater,container, false)
        return binding.root
    }

    // ngoprek layout setelah di load
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init the viewModel
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh() // load/fetch/refresh data

        // testing file
        // viewModel.testSaveFile()

        // setup recycle view
        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = studentListAdapter

        // swipe refresh
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        // observe - live data - arraylist student
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            // menghasilkan arraylist of student
            // mengupdate arraylist adapter
            studentListAdapter.updateStudentList(it)
        })

        // observe - live data - loadingLD
        viewModel.loadingDL.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // still loading
                binding.progressLoad.visibility = View.VISIBLE
                binding.recViewStudent.visibility = View.INVISIBLE
            } else {
                // data sudah terload / nda loading
                binding.progressLoad.visibility = View.INVISIBLE
                binding.recViewStudent.visibility = View.VISIBLE
            }
        })

        // observe - live data - errorLD
        viewModel.errorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                // ada error
                binding.txtError.text = "Something wrong when load student data"
                binding.txtError.visibility = View.VISIBLE
                binding.recViewStudent.visibility = View.INVISIBLE
            } else {
                // tidak ada error
                binding.txtError.visibility = View.INVISIBLE
                binding.recViewStudent.visibility = View.VISIBLE
            }
        })
    }
}