package com.digital.books.service;

import com.digital.books.handlers.DataNotFoundException;
import com.digital.books.model.DigitalBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
public class DigitalBookServiceImpl implements IDigitalBookService {

    private static final String YES = "yes";

    @Autowired
    private DigitalBooksRepository digitalBooksRepository;

    @Override
    public DigitalBook createDigitalBook(DigitalBook digitalBook,
                                         Integer authorId) {

        digitalBook.setAuthorId(authorId);
        var persistedBook = digitalBooksRepository.save(digitalBook);
        return persistedBook;
    }

    @Transactional
    @Override
    public DigitalBook updateDigitalBook(DigitalBook digitalBook,
                                         Integer authorId,
                                         Integer bookId) {
        System.out.println(String.format("Entered updateDigitalBook() method with DigitalBook Id: and authorId: %d",bookId,authorId));

        var existedBook = validateAuthorIdAndBookId(authorId, bookId);
        updateDigitalBookEntity(digitalBook, existedBook);
        digitalBooksRepository.saveAndFlush(existedBook);
        System.out.println(String.format("Saved  DigitalBook with Id: %d and authorId: %d",bookId,authorId));

        return existedBook;
    }

    @Override
    public String updateBookVisibility(Integer authorId, Integer bookId, String block) {
        System.out.println(String.format("Entered updateBookVisibility() method with DigitalBook Id: %d and authorId: %d",bookId,authorId));
        var existedBook = validateAuthorIdAndBookId(authorId, bookId);
        existedBook.setBlock(block);
        digitalBooksRepository.saveAndFlush(existedBook);

        if(YES.equalsIgnoreCase(block)){
            return String.format("DigitalBook Id %d blocked by authorId: %d",bookId,authorId);
        }
        return String.format("DigitalBook Id: %d unblocked by authorId: %d",bookId,authorId);
    }

    private DigitalBook validateAuthorIdAndBookId(Integer authorId, Integer bookId) {
        var existedBook = digitalBooksRepository.findBookByIdAndAuthorId(bookId,authorId);

        if (isNull(existedBook)){
            System.out.println(String.format("Not Data Found for authorId: %d and bookId: %d",authorId,bookId));
            throw new DataNotFoundException(String.format("Not Data Found for authorId: %d and bookId: %d",authorId,bookId),404);
        }
        return existedBook;
    }

    private void updateDigitalBookEntity(DigitalBook digitalBook, DigitalBook existedBook) {
        existedBook.setLogo(digitalBook.getLogo());
        existedBook.setTitle(digitalBook.getTitle());
        existedBook.setAuthor(digitalBook.getAuthor());
        existedBook.setPrice(digitalBook.getPrice());
        existedBook.setPublisher(digitalBook.getPublisher());
        existedBook.setActive(digitalBook.getActive());
        existedBook.setBlock(digitalBook.getBlock());
        existedBook.setCategory(digitalBook.getCategory());
        existedBook.setChapter(digitalBook.getChapter());
    }
}
