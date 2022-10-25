package com.example.curriculumvitaev2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [infofragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class infofragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var sharedPreferences: SharedPreferences
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_infofragment, container, false)
        lateinit var sharedPreferences: SharedPreferences
        val namei: TextView = view.findViewById(R.id.nameinfo)
        val agei: TextView = view.findViewById(R.id.ageinfo)
        val genderi: TextView = view.findViewById(R.id.genderinfo)
        val emaili: TextView = view.findViewById(R.id.emailinfo)
        sharedPreferences = this.requireActivity().getSharedPreferences("savefile", Context.MODE_PRIVATE)
        namei.setText("Hello! My name is "+sharedPreferences.getString("Name",""))
        agei.setText("I have "+sharedPreferences.getString("Age","")+" yearsold")
        genderi.setText("And i am a "+sharedPreferences.getString("Gender",""))
       emaili.setText(sharedPreferences.getString("Email",""))

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment infofragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            infofragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}