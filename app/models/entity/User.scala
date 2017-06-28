package models.entity

import org.squeryl.annotations._
import play.api.libs.json.Json

/**
  * Created by anton on 09.06.17.
  */
case class User (id: String, name: String, @Column("phone_number") phoneNumber: String)

object User {
  implicit val jsonUser = Json.format[User]
}
