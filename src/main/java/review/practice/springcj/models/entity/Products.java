package review.practice.springcj.models.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Products.TABLENAME)
public class Products extends BaseModels{
    public static final String TABLENAME ="products";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "productname", nullable = false, length = 200)
    private String name;

    @Column(name = "deskripsi")
    private String deskripsi;


    @ManyToOne
    @JoinColumn (name="product_id_category")
    private CategoryProduct productCategory;
}
