package com.example.domain

import io.reactivex.Flowable
import org.koin.core.KoinComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import org.koin.core.inject
import org.reactivestreams.Publisher
import retrofit2.HttpException
import io.reactivex.functions.Function

object TokenManager : KoinComponent {

    val hasToken: Boolean
        get() = token.isNotBlank()
    var token: String = ""
    var refreshToken: String = ""

    fun getAuthenticationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader(
                "Authorization",
                "Token $token"
            )
            return@Interceptor chain.proceed(builder.build())
        }
    }

//    private fun getTokenRefreshRequest(): Flowable<Result<Token>> {
////        return tokenRepository.refreshToken(refreshToken)
////            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
////            .doOnSuccess {
////                if (it is Result.Success) {
////                    token = it.data.token!!
////                    refreshToken = it.data.refresh!!
////                }
////            }.toFlowable()
////    }

    fun getRetry(): Function<Flowable<Throwable>, Publisher<*>> {
        return Retry()
    }

    class Retry : Function<Flowable<Throwable>, Publisher<*>> {
        override fun apply(t: Flowable<Throwable>): Publisher<*> {
            return t.take(2).flatMap {
                return@flatMap Flowable.error<TokenException>(it)
//                return@flatMap if (isUnAuthorizedError(it)) {
//                    getTokenRefreshRequest()
//                } else {
//                    Flowable.error<HttpException>(it)
//                }
            }
        }

        private fun isUnAuthorizedError(throwable: Throwable): Boolean {
            return when (throwable) {
                is TokenException -> {
                    throwable.code() == 401
                }
                else -> false
            }
        }
    }
}