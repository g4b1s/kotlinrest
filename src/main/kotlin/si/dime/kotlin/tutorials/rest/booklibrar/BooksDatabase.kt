package si.dime.kotlin.tutorials.rest.booklibrar

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus
import javax.annotation.PostConstruct

@Component
class BooksDatabase {
    private val books = mutableListOf<Book>()

    @PostConstruct
    private fun init() {
        books.add(Book(
                "0345391802",
                "The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams",
                coverURL = "https://d.gr-assets.com/books/1327656754l/11.jpg"))
        books.add(Book(
                "0345391803",
                "The Hitchhiker's Guide to the Galaxy1",
                "Douglas Adams",
                coverURL = "https://d.gr-assets.com/books/1327656754l/11.jpg"))

        books.add(Book(
                "076531178X",
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                coverURL = "https://d.gr-assets.com/books/1437254833l/68428.jpg"))
    }


    fun getBooks() = books

    fun addBook(book: Book): Boolean {
        books.firstOrNull { it.ISBN == book.ISBN }?.let { return false }
        books.add(book)
        return true
    }

    fun getisbn(ISBN: String): Book? {
        return books.find { book: Book -> book.ISBN == ISBN }
    }

    fun removeIsbn(ISBN: String): Boolean {
        return books.removeIf { book: Book -> book.ISBN == ISBN }
    }

    fun updateisbn(bookr: Book): Boolean{
        //books.replaceAll { book: Book -> book}?.let { return true }
        return books.find { book: Book -> book.ISBN == bookr.ISBN }.let {
            books.remove(it)
            books.add(bookr)
        }
    }

}

@ResponseStatus (value = org.springframework.http.HttpStatus.CONFLICT, reason = "Duplicate Book.")
class DuplicateItemException: RuntimeException(){}

@ResponseStatus (value= org.springframework.http.HttpStatus.NOT_FOUND, reason = "Not Found.")
class NotFoundItemException: RuntimeException(){}

