package com.project.ecommerce.service.seller;

import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.SellerRepository;
import com.project.ecommerce.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final SellerRepository sellerRepository;

    @Override
    public Seller add(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteById(Long id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getById(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller.orElseThrow(() -> new NotFoundException("seller couldn't be found by following id: " + id));
    }
}
