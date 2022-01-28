package review.practice.springcj.models.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = CategoryProduct.TABLENAME)
public class CategoryProduct extends BaseModels{
    public static final String TABLENAME ="productcategory";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_category_id", nullable = false)
    private Integer productCategoryId;

    @Column(name = "nama_kat_bar", nullable = false, length = 200)
    private String categoryName;

    @Column(name = "deskripsi")
    private String deskripsi;
}
