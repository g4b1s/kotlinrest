package si.dime.kotlin.tutorials.rest.booklibrar

import com.sun.org.apache.xpath.internal.operations.Bool
import com.sun.xml.internal.ws.resources.WsservletMessages
import org.eclipse.jetty.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import java.security.cert.CertPathValidatorException
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
                "0345391802",
                "The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams",
                coverURL = "https://d.gr-assets.com/books/1327656754l/11.jpg"))

        books.add(Book(
                "076531178X",
                "Mistborn: The Final Empire",
                "Brandon Sanderson",
                coverURL = "https://d.gr-assets.com/books/1437254833l/68428.jpg"))
    }


    fun getBooks() = books

    @RequestMapping("", method = arrayOf(RequestMethod.POST))
    fun addBook(book: Book): Boolean{
        books.firstOrNull{ it.ISBN == book.ISBN}?.let { return false }
        books.add(book)
        return true
    }

//    @RequestMapping("", method = arrayOf(RequestMethod.POST))
//    fun getisbn(): Book{
//        //val boo
//
//    }


}


@ResponseStatus (value = org.springframework.http.HttpStatus.CONFLICT, reason = "Duplicate Book.")
     class DuplicateItemException: RuntimeException(){}