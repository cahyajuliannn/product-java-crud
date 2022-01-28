package review.practice.springcj.controllers.apis;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import review.practice.springcj.models.entity.CategoryProduct;
import review.practice.springcj.repositories.CategoryProductRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/CategoryProduct")
public class CategoryProductController {
    @Autowired
    private CategoryProductRepository _cpRepository;

    @Autowired
    private ModelMapper _modelMapper;

    @GetMapping()
    public List<CategoryProduct> getAll(){
        try{
            return _cpRepository.findAllByOrderByCreatedAtDesc();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public CategoryProduct getById(@PathVariable Integer id){
        try{
            return _cpRepository.findById(id).get();
        } catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/save")
    public CategoryProduct save(@RequestBody CategoryProduct cp){
        try{
            Date datetime = new Date();
            datetime.getTime();
            Boolean isDel = false;
            cp.setCreatedAt(datetime);
            cp.setIsDeleted(isDel);
            return _cpRepository.save(cp);
        }catch (Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        try{
            _cpRepository.deleteById(id);
        } catch (Exception ex){
            throw ex;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryProduct> edit (@PathVariable Integer id, @Valid @RequestBody CategoryProduct cp){
        try{
            Date datetime = new Date();
            datetime.getTime();
            Boolean isDel = false;

            CategoryProduct idcp = _cpRepository.findById(id).get();
            cp.setIsDeleted(isDel);
            cp.setModifiedAt(datetime);
            _modelMapper.map(cp, idcp);
            final CategoryProduct editSave = _cpRepository.save(idcp);

            return ResponseEntity.ok(editSave);
        }catch (Exception ex){
            throw ex;//ddadsa
        }
    }
}
