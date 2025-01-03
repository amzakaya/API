package com.example.reservation.service;

import com.example.reservation.model.Business;
import com.example.reservation.model.User;
import com.example.reservation.repository.BusinessRepository;
import com.example.reservation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public BusinessServiceImpl(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Business> getAllBusinesses() {
        // Tüm işletmeleri listele
        return businessRepository.findAll();
    }

    @Override
    public Business saveBusiness(Business business) {
        // İşletme adının benzersizliğini kontrol et
        Optional<Business> existingBusiness = businessRepository.findByName(business.getName());
        if (existingBusiness.isPresent()) {
            throw new IllegalArgumentException("Bu isimle zaten bir işletme mevcut: " + business.getName());
        }

        // Owner ID'nin varlığını kontrol et
        Optional<User> owner = userRepository.findById(business.getOwner().getId());
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Belirtilen owner_id mevcut değil: " + business.getOwner().getId());
        }

        // Owner bilgisini tam olarak bağla
        business.setOwner(owner.get());

        // İşletmeyi kaydet
        return businessRepository.save(business);
    }

    @Override
    public List<Business> findBusinessesByCategory(String category) {
        // Belirtilen kategoriye göre işletmeleri bul
        return businessRepository.findByCategory(category);
    }
}
