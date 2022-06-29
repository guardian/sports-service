package com.gu.sports.dataaccess

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.model.PutObjectResult
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.gu.sports.util.Configuration
import dispatch.Future

import scala.util.{Failure, Success, Try}


trait S3Client {
  val s3: AmazonS3 = AmazonS3ClientBuilder.standard
    .withRegion(Regions.EU_WEST_1)
    .build()

  def put(content: String, path: String): Future[PutObjectResult] = {
    Try {
      s3.putObject(Configuration.bucketName, path, content)
    } match {
      case Success(r) => Future.successful(r)
      case Failure(er) => Future.failed(er)
    }

  }
}

object S3Client extends S3Client