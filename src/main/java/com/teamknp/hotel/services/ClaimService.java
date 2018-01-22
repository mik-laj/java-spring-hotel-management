package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Claim;
import com.teamknp.hotel.entity.Explanation;
import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.form.ClaimForm;
import com.teamknp.hotel.form.ExplanationForm;
import com.teamknp.hotel.form.ResolveForm;
import com.teamknp.hotel.repository.ClaimRepository;
import com.teamknp.hotel.repository.ExplanationRepository;
import com.teamknp.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClaimService {
    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    ExplanationRepository explanationRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserService userService;

    public Page<Claim> findAll(Pageable pageable) {
        return claimRepository.findAll(pageable);
    }

    public void delete(Claim claim) {
        claimRepository.delete(claim);
    }

    public Claim addClaim(ClaimForm formData) {
        Claim claim = new Claim();
        claim.setFirstName(formData.getFirstName());
        claim.setLastName(formData.getLastName());
        claim.setPhone(formData.getPhone());
        claim.setContent(formData.getContent());

        if(formData.getReservation() != null && formData.getReservation() > 0) {
            Reservation reservation = reservationRepository.findOne(formData.getReservation());
            claim.setReservation(reservation);
        }

        claim.setCreatedBy(userService.getCurrentUser());
        claim.setLastModifiedBy(userService.getCurrentUser());
        claim.setCreatedDate(new Date());
        claim.setLastModifiedDate(new Date());

        claimRepository.save(claim);

        return claim;
    }

    public Explanation addExplanation(Claim claim, ExplanationForm explanationForm){
        Explanation explanation = new Explanation();
        explanation.setClaim(claim);
        explanation.setContent(explanationForm.getContent());
        explanation.setCreatedBy(userService.getCurrentUser());
        explanation.setCreatedDate(new Date());
        explanationRepository.save(explanation);
        return explanation;
    }

    public void resolveClaim(Claim claim, ResolveForm formData) {
        Explanation explanation = new Explanation();
        explanation.setClaim(claim);
        explanation.setContent(formData.getContent());
        explanation.setCreatedBy(userService.getCurrentUser());
        explanation.setCreatedDate(new Date());
        explanationRepository.save(explanation);
        claim.setStatus("positive".equals(formData.getStatus()) ? Claim.Status.POSITIVE : Claim.Status.NEGATIVE);
        claimRepository.save(claim);
    }
}
