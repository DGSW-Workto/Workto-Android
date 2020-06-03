package com.example.domain

import retrofit2.HttpException
import retrofit2.Response

class TokenException(response: Response<*>) : HttpException(response) {

}