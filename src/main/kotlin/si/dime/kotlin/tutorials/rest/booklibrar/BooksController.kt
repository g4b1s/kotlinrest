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
    fun getisbn(@RequestBody  book: Book): Book? {
        database.getisbn(book.ISBN).let {
            if (it == null) throw NotFoundItemException()
            return it
        }
    }

    @RequestMapping("/iisbn/{isbnCode}",  method = arrayOf(RequestMethod.GET))
    fun getisbn(@PathVariable isbnCode: String): Book? {
        database.getisbn(isbnCode).let {
            if (it == null) throw NotFoundItemException()
            return it
        }

    }

    @RequestMapping("/rm", method = arrayOf(RequestMethod.POST))
    @ResponseStatus (value= org.springframework.http.HttpStatus.OK, reason = "OK")
    fun removeisbn(@RequestBody book: Book) =
            if(database.removeIsbn(book.ISBN)) true
    else throw NotFoundItemException()

    @RequestMapping("/update", method = arrayOf(RequestMethod.POST))
    fun updateisbn(@RequestBody book: Book)= if(database.updateisbn(book)) book  else throw NotFoundItemException()


}