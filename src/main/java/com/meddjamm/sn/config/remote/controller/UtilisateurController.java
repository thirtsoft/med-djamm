package com.meddjamm.sn.config.remote.controller;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.motdepasse.ChangerMotDePasseRequest;
import com.meddjamm.sn.config.remote.controller.api.UtilisateurApi;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.remote.model.UtilisateurProfilDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.utils.ApiUrlAccess;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.meddjamm.sn.utils.UtilString.getUrl;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Transactional
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;

    @Override
    public ResponseEntity<UtilisateurDs> creerUtilisateur(UtilisateurDs utilisateurDs, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurAssembler.assembleUtilisateurFromDs(utilisateurDs);
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.
                        saveUtilisateur(utilisateur, getUrl(request))), HttpStatus.CREATED);
    }

    @Override
    public String activation(String code) {
        String FormConnexionFrontend =
                "<h1>Votre compte a bien été activié.,</h1>" + " <br>" +
                        "<h2> Veuillez cliquez sur le lien ci-dessous pour vous connectez.</h2>" +
                        "<h2> Avec votre email et le mot de passe que vous avez reçu par émail.</h2>" +
                        "<a href=\"" + ApiUrlAccess.HOST_FRONT + "\">Se connecter</a>";
        utilisateurService.lireEnFonctionDuCode(code);
        return FormConnexionFrontend;
    }

    @Override
    public ResponseEntity<UtilisateurDs> updateUtilisateur(UtilisateurDs utilisateurDs) {
        Utilisateur utilisateur = utilisateurAssembler.assembleUtilisateurForUpdateDs(utilisateurDs);
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.updateUserPass(utilisateur)), OK);
    }

    @Override
    public ResponseEntity<UtilisateurDs> findUtilisateurById(Long id) {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.findUtilisateurById(id)), OK);
    }

    @Override
    public ResponseEntity<List<UtilisateurDs>> findAllUtilisateurs() {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleEntitiesFrom(utilisateurService.findAllUtilisateurs()), OK);
    }

    @Override
    public ResponseEntity<Void> deleteUtilisateur(String email) {
        utilisateurService.deleteUtilisateur(email);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<UtilisateurProfilDs> findUtilisateurProfil(Long id) {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurProfilDsFromEntity(utilisateurService.findUserById(id)), OK);
    }

    @Override
    public ResponseEntity<String> changerMotDePasseRequest(ChangerMotDePasseRequest changerMotDePasseRequest,
                                                           HttpServletRequest request) {
        String url = getUrl(request) + "/med-dalaljamm/v1/utilisateur/reset-password?token=";
        return new ResponseEntity<>(utilisateurService.demandeChangerMotDePasse(changerMotDePasseRequest.getEmail(), url), CREATED);
    }

    @Override
    public String resetPassword(ChangerMotDePasseRequest changerMotDePasseRequest, String token) {
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

    @Override
    public ResponseEntity<String> changePassword(ChangerMotDePasseRequest requestUtil) {
        Utilisateur user = utilisateurService.findUtilisateurByEmail(requestUtil.getEmail());
        if (!utilisateurService.oldPasswordIsValid(user, requestUtil.getAncienMotDePasse())) {
            return new ResponseEntity<>("Incorrect old password", HttpStatus.BAD_REQUEST);
        }
        utilisateurService.changePassword(user, requestUtil.getNouveauMotDePasse());
        return new ResponseEntity<>("Password changed successfully", CREATED);
    }

    @Override
    public ResponseEntity<List<UtilisateurDs>> findAllMedecins() {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleEntitiesFrom(utilisateurService.findAllMedecins()), OK);
    }

    @Override
    public ResponseEntity<Void> activatedAccount(String matricule) {
        utilisateurService.activatedAccount(matricule);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<Void> deactivatedAccount(String matricule) {
        utilisateurService.deactivatedAccount(matricule);
        return new ResponseEntity<>(OK);
    }

}
