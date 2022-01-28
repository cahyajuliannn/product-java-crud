package review.practice.springcj.controllers.apis;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import review.practice.springcj.models.dto.ProductsDto;
import review.practice.springcj.models.entity.CategoryProduct;
import review.practice.springcj.models.entity.Products;
import review.practice.springcj.repositories.CategoryProductRepository;
import review.practice.springcj.repositories.ProductRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/Product")
public class ProductController {
    @Autowired
    private ProductRepository _pRepository;

    @Autowired
    private CategoryProductRepository _cpRepository;

    @Autowired
    private ModelMapper _modelMapper;

    @GetMapping
    public List<Products> getAll(){
        try{
            return _pRepository.findAll();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public Products getById(@PathVariable Integer id){
        try{
            return _pRepository.findById(id).get();
        } catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping()
    public ProductsDto save(@RequestBody ProductsDto pDto){
        try{
            Date today = new Date();
            Boolean isDel = false;
            Products p = new Products();
            CategoryProduct cp = new CategoryProduct();
            cp.setProductCategoryId(pDto.kategoriBarangId);
            p.setProductCategory(cp);
            _modelMapper.map(pDto, p);

            CategoryProduct newcp = _cpRepository.findById(pDto.getKategoriBarangId()).get();
            pDto.setKategoriBarangId(newcp.getProductCategoryId());
            pDto.setDeskripsi(newcp.getDeskripsi());
            pDto.setNamaKategoriBarang(newcp.getCategoryName());
            pDto.setDeskripsiKategoriBarang(newcp.getDeskripsi());
            pDto.setCreatedAt(today);
            pDto.setIsDeleted(isDel);
            _pRepository.save(p);
            _modelMapper.map(p, pDto);

            return pDto;
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try{
            _pRepository.deleteById(id);
        } catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> edit (@PathVariable Integer id, @Valid @RequestBody ProductsDto pDto){
        try{
            Date datemodif = new Date();
            datemodif.getTime();
            Boolean isDel = false;

            CategoryProduct cp = new CategoryProduct();
            cp.setProductCategoryId(pDto.getKategoriBarangId());

            Products newProduct = _pRepository.findById(id).get();
            newProduct.setName(pDto.getName());
            newProduct.setDeskripsi(pDto.getDeskripsi());
            newProduct.setIsDeleted(isDel);
            newProduct.setModifiedAt(datemodif);
            newProduct.setProductCategory(cp);

            _modelMapper.map(pDto, newProduct);

            final Products editSave = _pRepository.save(newProduct);
            return ResponseEntity.ok(editSave);
        } catch (Exception ex){
            throw ex;
        }
    }

}
