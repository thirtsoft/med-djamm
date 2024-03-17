package com.meddjamm.sn.config.remote.controller;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.motdepasse.ChangerMotDePasseRequest;
import com.meddjamm.sn.config.remote.controller.api.UtilisateurApi;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.config.service.impl.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.meddjamm.sn.utils.UtilString.getUrl;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
@Transactional
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;
    private final ValidationService validationService;

    @Override
    public ResponseEntity<UtilisateurDs> creerUtilisateur(UtilisateurDs utilisateurDs, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurAssembler.assembleUtilisateurFromDs(utilisateurDs);
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.
                        saveUtilisateur(utilisateur, getUrl(request))), CREATED);
    }

    @Override
    public ResponseEntity<Void> activation(String code) {
        utilisateurService.lireEnFonctionDuCode(code);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<UtilisateurDs> updateUtilisateur(Long id, UtilisateurDs utilisateurDs) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<UtilisateurDs> findUtilisateurById(Long id) throws Exception {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.findUtilisateurById(id)), OK);
    }

    @Override
    public ResponseEntity<List<UtilisateurDs>> findAllUtilisateurs() {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleEntitiesFrom(utilisateurService.findAllUtilisateurs()), OK);
    }

    @Override
    public void deleteUtilisateur(Long id) {
    }

    @Override
    public ResponseEntity<UtilisateurDs> findUtilisateurProfil(Long id) throws Exception {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.findUserById(id)), OK);
    }

    @Override
    public ResponseEntity<String> changerMotDePasseRequest(ChangerMotDePasseRequest changerMotDePasseRequest,
                                                           HttpServletRequest request) {
        String url = getUrl(request) + "/med-dalaljamm/v1/utilisateur/reset-password?token=";
        return new ResponseEntity<>(utilisateurService.demandeChangerMotDePasse(changerMotDePasseRequest.getEmail(), url), CREATED);
    }


    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ChangerMotDePasseRequest changerMotDePasseRequest, @RequestParam("token") String token) {
        String tokenVerificationResult = utilisateurService.validatePasswordResetToken(token);
        if (!tokenVerificationResult.equalsIgnoreCase("valide")) {
            return "Invalid token password reset token";
        }
        Optional<Utilisateur> theUser = Optional.ofNullable(utilisateurService.findUserByPasswordToken(token));
        if (theUser.isPresent()) {
            utilisateurService.changePassword(theUser.get(), changerMotDePasseRequest.getNouveauMotDePasse());
            return "Password has been reset successfully";
        }
        return "Invalid password reset token";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangerMotDePasseRequest requestUtil) {
        Utilisateur user = utilisateurService.findUtilisateurByEmail(requestUtil.getEmail());
        if (!utilisateurService.oldPasswordIsValid(user, requestUtil.getAncienMotDePasse())) {
            return "Incorrect old password";
        }
        utilisateurService.changePassword(user, requestUtil.getNouveauMotDePasse());
        return "Password changed successfully";
    }

}
