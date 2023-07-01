package com.radiusagent.demo.common.base_response

class Result<T> {
    var data: T?
        private set
    var status: Status
        private set
    var msg: String? = null
        private set
    val code = 0


    constructor(data: T,
                msg: String?,
                status: Status
    ) {
        this.data = data
        this.status = status
        this.msg = msg
    }

    val isSuccessful: Boolean
        get() = status == Status.SUCCESS
    val isFailed: Boolean
        get() = status == Status.FAILURE

    enum class Status {
        SUCCESS, FAILURE, INPROGRESS
    }
}