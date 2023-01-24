package com.borysboyko.toolbarmenuinflationlatencyexploration

import android.os.Bundle
import android.os.Handler
import android.os.Trace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.borysboyko.toolbarmenuinflationlatencyexploration.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.fragmentOwnedToolbar.title = "Fragment-owned App Bar"

        Trace.beginSection("menuInflater.inflate (Fragment-owned App Bar)")
        val start = System.currentTimeMillis()
        requireActivity().menuInflater.inflate(R.menu.menu_main, binding.fragmentOwnedToolbar.menu)
        var initialInflation = System.currentTimeMillis() - start
        Log.e("BORYS", "menuInflater.inflate (Fragment-owned App Bar) took: " + initialInflation + "ms to inflate 12 menu items")
        Trace.endSection()

        var text = ""
        Handler().postDelayed({
            text += "Activity-owned App Bar menu inflation time for 12 items: " + (requireActivity() as MainActivity).initialInflation + "ms\n"
            text += "Fragment-owned App Bar menu inflation time for 12 items: " + initialInflation + "ms\n"
            binding.textviewFirst.text = text
        }, 2000)

//        binding.buttonAdd.setOnClickListener {
//            var activityMenuAddTime = (requireActivity() as MainActivity).addToolbarMenuItems(null, true)
//            var fragmentMenuAddTime = (requireActivity() as MainActivity).addToolbarMenuItems(binding.fragmentOwnedToolbar, false)
//
//            text = binding.textviewFirst.text.toString()
//            text += "Add time for Activity-owned App Bar menu for 6 items: " + activityMenuAddTime + "ms\n"
//            text += "Add time for Fragment-owned App Bar menu for 6 items: " + fragmentMenuAddTime + "ms\n"
//            binding.textviewFirst.text = text
//        }
//
//        binding.buttonClear.setOnClickListener {
//            var activityMenuClearTime = (requireActivity() as MainActivity).clearAllToolbarMenuItems(null, true)
//            var fragmentMenuClearTime = (requireActivity() as MainActivity).clearAllToolbarMenuItems(binding.fragmentOwnedToolbar, false)
//
//            text = binding.textviewFirst.text.toString()
//            text += "Clear time for Activity-owned App Bar menu for all current items: " + activityMenuClearTime + "ms\n"
//            text += "Clear time for Fragment-owned App Bar menu for all current items: " + fragmentMenuClearTime + "ms\n"
//            binding.textviewFirst.text = text
//        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}