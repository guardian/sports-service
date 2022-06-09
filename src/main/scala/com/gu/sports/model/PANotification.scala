package com.gu.sports.model

case class Entity(
  id: Int,
  name: String,
  status: Option[String],
  date: Option[String],
)

case class PANotification(
  operation: String,
  timestamp: String,
  `type`: String,
  url: String,
  event: Entity,
  stage: Option[Entity],
  tournament: Option[Entity],
  season: Option[Entity],
  sport: Entity,
  `_ts`: Int,
)
