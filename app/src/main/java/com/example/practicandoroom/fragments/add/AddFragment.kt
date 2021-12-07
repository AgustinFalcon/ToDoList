package com.example.practicandoroom.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practicandoroom.R
import com.example.practicandoroom.data.entities.User
import com.example.practicandoroom.data.viewmodel.UserViewModel
import com.example.practicandoroom.databinding.FragmentAddBinding


class AddFragment : Fragment() {

   private lateinit var binding: FragmentAddBinding
   private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java) //DEPRECADO XD

        binding.btnAdd.setOnClickListener {
            insertDataToDataBase()
        }


        return binding.root
    }

    private fun insertDataToDataBase() {
        val name = binding.etName.text.toString()
        val lastname = binding.etLastname.text.toString()
        val age = binding.etAge.text

        if(checkInput(name,lastname,age)){
            //Created User
            val user = User(0,name = name, lastname = lastname, age = Integer.parseInt(age.toString()))
            //Add user in database
            userViewModel.addUser(user)
            //show the data
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_SHORT).show()
            //Go to recyclerview with the users
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(),"The fields can't be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInput(name: String, lastname: String, age: Editable) : Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(lastname) && age.isEmpty())
    }

}