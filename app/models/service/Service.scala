package models.service

/**
  * Created by anton on 10.06.17.
  */
trait Service[T] {
  def insert (data: T)
  def selectAll(): Seq[T]
  def selectById(id: String): T
  def delete(id: String)
  def update2(data: T)
}
