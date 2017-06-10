package models.entity

import org.squeryl.annotations._

/**
  * Created by anton on 09.06.17.
  */
  case class User (id: String, name: String, @Column("phone_number") phoneNumber: Long)
