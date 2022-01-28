package review.practice.springcj.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductsDto {
    public Integer id;
    public String name;
    public String deskripsi;
    public Integer kategoriBarangId;
    public String namaKategoriBarang;
    public String deskripsiKategoriBarang;

    private Date createdAt;
    private Date modifiedAt;
    private Date deletedAt;
    private Boolean isDeleted;
}
