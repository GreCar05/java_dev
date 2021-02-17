package repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
