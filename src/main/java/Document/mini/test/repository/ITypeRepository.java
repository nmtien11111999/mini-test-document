package Document.mini.test.repository;

import Document.mini.test.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepository extends JpaRepository<Type, Long> {
}