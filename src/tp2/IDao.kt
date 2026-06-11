package tp2

interface IDao<T> {
    fun create(o: T): Boolean
    fun update(o: T): Boolean
    fun delete(o: T): Boolean
    fun findAll(): List<T>
    fun findById(id: Int): T?
}
