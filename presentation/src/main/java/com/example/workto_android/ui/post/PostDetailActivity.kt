package com.example.workto_android.ui.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.domain.result.EventObserver
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityPostDetailBinding
import com.example.workto_android.ui.BaseActivity
import com.example.workto_android.ui.main.MainActivity
import com.example.workto_android.ui.team.TeamActivity
import com.example.workto_android.util.BASE_URL
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailActivity : BaseActivity<PostDetailViewModel>() {
    override val viewModel: PostDetailViewModel by viewModel()
    private lateinit var binding: ActivityPostDetailBinding

    private val fromTeam: Boolean by lazy {
        intent.getBooleanExtra("fromTeam", false)
    }

    private val groupId: Int? by lazy {
        val id = intent.getIntExtra("id", -1)
        if (id == -1) null else id
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPostDetailBinding>(
            this,
            R.layout.activity_post_detail
        ).apply {
            lifecycleOwner = this@PostDetailActivity
            viewModel = this@PostDetailActivity.viewModel
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        if (groupId == null) {
            makeToast("오류 발생. 잠시 후 다시 시도해주세요", false)
            finish()
        }

        viewModel.setGroupId(groupId!!)

        initObserver()
    }

    private fun initObserver() {
        viewModel.error.observe(this, EventObserver {
            when (it) {
                "network" -> Snackbar.make(
                    binding.holderLayout,
                    "네트워크 연결을 확인해주세요",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        viewModel.navigateToTeam.observe(this, EventObserver {
            if (fromTeam)
                finish()
            else
                startActivity(Intent(this, TeamActivity::class.java).putExtra("group_id", it))
        })
    }

}

@BindingAdapter("url")
fun setImage(imageView: ImageView, url: String?) {

    url?.let {
        val formattedUrl = BASE_URL + it.substring(1, it.length)
        imageView.run {
            Glide.with(context).load(formattedUrl).into(this)
        }
    }
}
