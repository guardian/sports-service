package com.gu.sports.api

import org.scalatra.Ok

class StatusAPI extends BaseAPI {

    get("/") {
        Ok("healthy")
    }
}
