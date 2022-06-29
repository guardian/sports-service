package com.gu.sports.dataaccess

import dispatch._, Defaults._

trait PAClient {
  def get(path: String, apiKey: String): Future[Res] =
    Http.default(
      url(path)
        .setHeader("apikey", apiKey)
        .GET)

}

object PAClient extends PAClient