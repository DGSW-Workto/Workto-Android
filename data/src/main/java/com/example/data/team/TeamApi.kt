package com.example.data.team

import com.example.model.Response
import com.example.model.TeamData
import com.example.model.TeamMemberData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApi {

    @GET("/team/list")
    fun getTeamList(@Query("is_mine") isMine: String, @Query("page_num") page: String) : Single<retrofit2.Response<Response<TeamData>>>

    @GET("/team/getmember")
    fun getTeamMember(@Query("is_volunteer") isVolunteer: String, @Query("group_id") groupId: String) : Single<retrofit2.Response<Response<TeamMemberData>>>

}