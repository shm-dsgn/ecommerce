package com.project.ecommerce.service;

import com.project.ecommerce.model.CreditCard;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.CreditCardRepository;
import com.project.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{

    private final CreditCardRepository creditCardRepository;

    private final UserService userService;

    @Override
    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard add(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCardByUserId(Long id) {
        Optional<User> user = Optional.ofNullable(userService.getById(id));

        if (user.isPresent()) {
            CreditCard creditCard = user.get().getCreditCard();
            return List.of(creditCard);
        }
        return null;
    }

    @Override
    public CreditCard getBydId(Long id) {
        Optional<CreditCard> creditCard = creditCardRepository.findById(id);
        return creditCard.orElse(null);
    }
}
