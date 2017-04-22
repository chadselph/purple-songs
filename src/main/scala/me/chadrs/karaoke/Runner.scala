package me.chadrs.karaoke

import com.amazonaws.services.lambda.runtime.Context
import com.itv.chuckwagon.lambda._
import io.circe.Encoder
import io.circe.Encoder._
import SongEncoder._


object SongEncoder {
  implicit val songEncoder: Encoder[Song] = Encoder.forProduct2("artist", "title")(s => (s.artist, s.title))
}

/**
  * Return the JSON of the songs to lambda
  */
class Runner extends Handler[Unit, Seq[Song]] {

  override protected def handler(input: Unit, context: Context): Seq[Song] = {
    FetchBookTask.parse(
      FetchBookTask.downloadContents()
    )
  }
}
