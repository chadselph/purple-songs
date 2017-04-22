package me.chadrs.karaoke

import org.jsoup.Jsoup
import scala.collection.JavaConverters._

import scalaj.http.{HttpResponse, Http}


case class Song(artist: String, title: String) extends Serializable

object FetchBookTask {

  def downloadContents(): String = {
    val content: HttpResponse[String] = Http("http://djpurple.com/blog/songbook/")
      .header("User-Agent", "songbook-fetcher").asString
    content.body
  }

  def parse(contents: String): Seq[Song] = {
    val doc = Jsoup.parse(contents)
    doc.select("p").asScala.collect {
      case element if element.text.contains("–") =>
        val parts = element.text.split(" – ")
        Song(parts(0), parts(1))
    }
  }

}
