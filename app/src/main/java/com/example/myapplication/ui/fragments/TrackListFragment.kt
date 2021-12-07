package com.example.myapplication.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTrackListBinding
import com.example.myapplication.model.Track
import com.example.myapplication.repo.TrackListRepo
import com.example.myapplication.ui.list.TrackListAdapter

class TrackListFragment:Fragment(R.layout.fragment_track_list) {
    private var trackAdapter:TrackListAdapter?=null;
    var binding: FragmentTrackListBinding?=null;
    lateinit var track:Track
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackListBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trackAdapter= TrackListAdapter(TrackListRepo.trackList){id:Int ->
            val args = Bundle().also {
                it.putInt("id",id)
            }
            fragmentManager?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.fragment_container, TrackDetailFragment().apply {
                    arguments = args
                })
                ?.commit()
        }

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding?.rvTracks?.adapter=trackAdapter
        view.findViewById<RecyclerView>(R.id.rv_tracks).addItemDecoration(decorator)
        view.findViewById<RecyclerView>(R.id.rv_tracks).layoutManager=LinearLayoutManager(this.context)


}
}
