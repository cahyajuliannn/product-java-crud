package review.practice.springcj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import review.practice.springcj.models.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
}
