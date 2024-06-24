package Document.mini.test.repository;

import Document.mini.test.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentRepository extends JpaRepository<Document, Long> {

    Page<Document> findAll(Pageable pageable);

    Page<Document> findAllByNameContaining(Pageable pageable, String name);

}
