package br.com.jlgregorio.MyStore.repository;

import br.com.jlgregorio.MyStore.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
