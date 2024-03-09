package com.project.ecommerce.service.seller;

import com.project.ecommerce.exception.NotFoundException;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.model.SellerComment;
import com.project.ecommerce.repository.SellerCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerCommentServiceImpl implements SellerCommentService{

    private final SellerCommentRepository sellerCommentRepository;

    private final SellerService sellerService;

    @Override
    public SellerComment add(SellerComment sellerComment) {
        if (sellerComment.getRating() < 1) {
            return null;
        } else {
            return sellerCommentRepository.save(sellerComment);
        }
    }

    @Override
    public void deleteById(Long id) {
        sellerCommentRepository.deleteById(id);
    }

    @Override
    public List<SellerComment> getAll() {
        return sellerCommentRepository.findAll();
    }

    @Override
    public SellerComment getById(Long id) {
        Optional<SellerComment> sellerComment = sellerCommentRepository.findById(id);
        return sellerComment.orElseThrow(() -> new NotFoundException("seller comment couldn't be found by following id: " + id));
    }

    @Override
    public List<SellerComment> getSellerCommentsBySeller(Long id) {
        Optional<Seller> seller = Optional.ofNullable(sellerService.getById(id));
        List<SellerComment> responseSellerComments = new ArrayList<>();

        if (seller.isPresent()) {
            for (SellerComment sellerComment : seller.get().getSellerComment()) {
                responseSellerComments.add(sellerComment);
                return responseSellerComments;
            }
        }
        return null;
    }
}