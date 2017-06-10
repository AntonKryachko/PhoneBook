package module

import play.api.libs.json.Json

/**
  * Created by anton on 09.06.17.
  */
case class User (name: String, phoneNumber: Long)

object User{
  implicit val jsonUser = Json.format[User]
}