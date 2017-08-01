package si.dime.kotlin.tutorials.rest.booklibrar

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
        class BooksController{

    @Autowired
    private lateinit var database: BooksDatabase

    @RequestMapping("/all", method = arrayOf(RequestMethod.GET))
    fun books() = database.getBooks()

    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addbook(@RequestBody book: Book) =
            if (database.addBook(book)) book
    else throw DuplicateItemException()

    @RequestMapping("/isbn",  method = arrayOf(RequestMethod.GET))
    //fun getisbn(@RequestBody  ISBN: String) = database.getisbn(ISBN)
    fun getisbn(@RequestBody  book: Book) = database.getisbn(book.ISBN)


}