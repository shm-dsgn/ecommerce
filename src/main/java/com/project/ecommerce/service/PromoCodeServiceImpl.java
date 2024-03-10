package com.project.ecommerce.service;

import com.project.ecommerce.exception.NotFoundException;
import com.project.ecommerce.model.PromoCode;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.PromoCodeRepository;
import com.project.ecommerce.request.PromoCodeCreateRequest;
import com.project.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService{

    private final UserService userService;

    private final PromoCodeRepository promoCodeRepository;

    private final CreateCodeService createCodeService;

    private final SendEmailService sendEmailService;

    @Override
    public String createPromoCode(PromoCodeCreateRequest promoCodeRequest) {
        Optional<User> user = Optional.ofNullable(userService.getById(promoCodeRequest.getUserId()));

        if (user.isPresent()) {
            PromoCode promoCode = new PromoCode();
            promoCode.setCode(createCodeService.createCode() + promoCodeRequest.getAmount());
            promoCode.setCreateDate(new Date());
            promoCode.setAmount(promoCodeRequest.getAmount());
            promoCode.setExpirationDate(new Date(promoCodeRequest.getYear(), promoCodeRequest.getMonth(), promoCodeRequest.getDate()));
            promoCode.setUser(user.get());

            promoCodeRepository.save(promoCode);
            userService.add(user.get());

            sendEmailService.sendEmails(user.get().getEmail(), "You have promo code\n Expiration Date: "+ promoCode.getExpirationDate() + "\nCode: "
                    + promoCode.getCode(), "You Have Promo Code");
            return promoCode.getCode();
        }

        return "failed";
    }

    @Override
    public List<PromoCode> getAll() {
        return promoCodeRepository.findAll();
    }

    @Override
    public PromoCode getById(Long id) {
        return promoCodeRepository.findById(id).orElseThrow(() -> new NotFoundException("Promo code couldn't be found by following id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        promoCodeRepository.deleteById(id);
    }
}
