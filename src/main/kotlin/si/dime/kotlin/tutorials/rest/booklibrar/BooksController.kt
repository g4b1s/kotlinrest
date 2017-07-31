package si.dime.kotlin.tutorials.rest.booklibrar

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
        class BooksController{

    @Autowired
    private lateinit var database: BooksDatabase

    @RequestMapping("/all", method = arrayOf(RequestMethod.GET))
    fun books() = database.getBooks()

//    @RequestMapping("/isbn", method = arrayOf(RequestMethod.GET))
//    fun getisbn() = database.getisbn()

    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addbook(@RequestBody book: Book) =
            if (database.addBook(book)) book
    else throw DuplicateItemException()

}