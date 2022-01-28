package review.practice.springcj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import review.practice.springcj.models.entity.CategoryProduct;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
    @Query(value = "SELECT * FROM productcategory k ORDER BY created_at DESC", nativeQuery = true)
    List<CategoryProduct> findAllByOrderByCreatedAtDesc();
}
