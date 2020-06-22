package com.example.workto_android.ui.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.result.EventObserver
import com.example.model.TeamMemberData
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityTeamBinding
import com.example.workto_android.ui.BaseActivity
import com.example.workto_android.ui.main.PostAdapter
import com.example.workto_android.ui.post.PostDetailActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamActivity : BaseActivity<TeamViewModel>() {

    private lateinit var binding: ActivityTeamBinding

    override val viewModel: TeamViewModel by viewModel()

    private val groupId by lazy {
        val id = intent.getIntExtra("group_id", -1)
        if (id != -1) id else null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityTeamBinding>(this, R.layout.activity_team)
            .apply {
                lifecycleOwner = this@TeamActivity
                viewModel = this@TeamActivity.viewModel
            }

        if (groupId == null)
            finish()

        viewModel.setGroupId(groupId!!)

        binding.recyclerviewTeamMember.apply {
            setHasFixedSize(true)
            adapter = TeamMemberAdapter()
        }

        binding.recyclerviewPost.apply {
            setHasFixedSize(true)
            adapter = PostAdapter(viewModel)
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.navigateToPostDetail.observe(this, EventObserver {
            startActivity(
                Intent(this, PostDetailActivity::class.java).putExtra("id", it)
                    .putExtra("fromTeam", true)
            )
        })

        viewModel.error.observe(this, EventObserver {
            Snackbar.make(binding.holderLayout, it, Snackbar.LENGTH_LONG).show()
        })
    }

}

@BindingAdapter("team_member_item")
fun setTeamMember(recyclerView: RecyclerView, teamMemberData: TeamMemberData?) {

    val teamMemberAdapter: TeamMemberAdapter

    if (recyclerView.adapter == null)
        return
    else
        teamMemberAdapter = recyclerView.adapter as TeamMemberAdapter

    teamMemberData?.let {
        teamMemberAdapter.teamMemberList = it.member
    }
}
