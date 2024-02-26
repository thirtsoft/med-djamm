package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.rh.entity.Profil;
import com.meddjamm.sn.entity.Utilisateur;
import com.meddjamm.sn.rh.repository.ProfilRepository;
import com.meddjamm.sn.repository.UtilisateurrRepository;
import com.meddjamm.sn.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurrRepository utilisateurrRepository;

    private final ProfilRepository profilRepository;

    public UtilisateurServiceImpl(UtilisateurrRepository utilisateurrRepository,
                                  ProfilRepository profilRepository) {
        this.utilisateurrRepository = utilisateurrRepository;
        this.profilRepository = profilRepository;
    }

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws Exception {
        if (utilisateur == null)
            throw new Exception("L'objet à sauvegarder est nul");
        if (utilisateur.getPrenom() == null
                || (utilisateur.getPrenom() != null && utilisateur.getPrenom().equals("")))
            throw new Exception("Le prénom est obligatoire");
        if (utilisateur.getNom() == null || (utilisateur.getNom() != null && utilisateur.getNom().equals("")))
            throw new Exception("Le nom est obligatoire");
        if (utilisateur.getEmail() == null
                || (utilisateur.getEmail() != null && utilisateur.getEmail().equals("")))
            throw new Exception("Le mail est obligatoire");
        Utilisateur userSearch = utilisateurrRepository.findByMail(utilisateur.getEmail());
        if (userSearch != null)
            if (utilisateur.getId() == null
                    || (utilisateur.getId() != null && !utilisateur.getId().equals(utilisateur.getId())))
                throw new Exception("Ce mail est déjà utilisé");
        if (utilisateur.getCodeUtilisateur() == null
                || (utilisateur.getCodeUtilisateur() != null && utilisateur.getCodeUtilisateur().equals("")))
            throw new Exception("L'identifiant est obligatoire");
        userSearch = utilisateurrRepository.findByCodeUtilisateur(utilisateur.getCodeUtilisateur());
        if (userSearch != null)
            if (utilisateur.getId() == null
                    || (utilisateur.getId() != null && !utilisateur.getId().equals(utilisateur.getId())))
                throw new Exception("Ce Code utilisateur est déjà utilisé");
        if (utilisateur.getMotdepasse() == null
                || (utilisateur.getMotdepasse() != null && utilisateur.getMotdepasse().equals("")))
            throw new Exception("Le mot de passe est obligatoire");
        if (utilisateur.getProfilId() == null)
            throw new Exception("Le profil est obligatoire");
        /*
        Pattern pattern = Pattern.compile(ConstantSigps.REGEX_MAIL);
        Matcher matcher = pattern.matcher(utilisateurDTO.getEmail());
        if (!matcher.matches()) {
            throw new Exception("Le format du mail est incorrect");
        }*/
        Profil profil = profilRepository.findById(utilisateur.getProfilId()).get();
        //  utilisateurDTO.setProfilDTO(dtoFactory.createProfilDTO(profil));
        utilisateur.setProfilId(profil.getId());
        utilisateur.setEst_valide(true);
        if (utilisateur.getId() == null) {
            utilisateur.setDateCreation(new Date());
            //   utilisateur.setMotdepasse(bCryptPasswordEncoder.encode(utilisateur.getMotdepasse()));
            utilisateur.setMdpamodifier(true);
            utilisateur.setDateModPass(new Date());
        }
        /*
        utilisateur = modelFactory.createUtilisateur(utilisateurDTO);
        if (utilisateurDTO.getId() == null) {
            do {
                utilisateur.setActivation(RandomStringUtils.randomAlphanumeric(15));
            }while(utilisateurRepository.findUtilisateurByActivation(utilisateur.getActivation()) != null);

            utilsRepositoryCustom.sendMailCreateUser(utilisateur);
        }*/
        userSearch = utilisateurrRepository.saveAndFlush(utilisateur);
/*
        if (utilisateurDTO.getId() == null) {
            journalService.logJournal(this.getClass().getMethods(), "saveUtilisateur",
                    "Création d'un utilisateur " + utilisateurDTO.getCodeUtilisateur(), "Utilisateur",
                    utilisateur.getId());
        } else {
            journalService.logJournal(this.getClass().getMethods(), "saveUtilisateur",
                    "Modification d'un utilisateur " + utilisateurDTO.getCodeUtilisateur(), "Utilisateur",
                    utilisateur.getId());
        }*/
        return utilisateur;

    }

    @Override
    public Utilisateur findUtilisateurByCode(String code) throws Exception {
        if (code != null && "".equals(code))
            return null;
        return utilisateurrRepository.findByCodeUtilisateur(code);
    }

    @Override
    public Utilisateur findUtilisateurById(Long utilisateurId) throws Exception {
        if (utilisateurId == null)
            return null;
        return utilisateurrRepository.findUtilisateurById(utilisateurId);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurrRepository.findAll();
    }

    @Override
    public List<Utilisateur> findAllActives() {
        return utilisateurrRepository.findAllActive();
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur Utilisateur) throws Exception {
        return false;
    }

    @Override
    public boolean checkValiditePass(String mdp) throws Exception {
        if (mdp.length() < 8)
            throw new Exception("Le mot de passe doit comporter au moins 8 caractères");
        boolean maj = false, chif = false;
        for (int i = 0; i < mdp.length(); i++) {
            Character c = mdp.charAt(i);
            if (Character.isDigit(c))
                chif = true;
            if (Character.isUpperCase(c))
                maj = true;
        }
        if (!chif)
            throw new Exception("Le mot de passe doit comporter au moins un chiffre");
        if (!maj)
            throw new Exception("Le mot de passe doit comporter au moins une majuscule");
        return true;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String mail) throws Exception {
        if (mail != null && "".equals(mail))
            return null;
        return utilisateurrRepository.findByMail(mail);
    }

    @Override
    public List<Utilisateur> getListeUsers(List<Utilisateur> userDTOs, int page, int ligneParPage) {
        return null;
    }

    @Override
    public byte[] generateNewKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen;
        keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey cle = keyGen.generateKey();
        byte[] b = cle.getEncoded();
        return b;
    }

    @Override
    public void updateUserPass(Utilisateur utilisateur) throws Exception {
        utilisateurrRepository.save(utilisateur);
     //   utilsRepositoryCustom.sendMailUpdatePass(utilisateur);
    }

    @Override
    public void resetUserPass(Utilisateur utilisateur) throws Exception {
        utilisateurrRepository.save(utilisateur);
     //   utilsRepositoryCustom.sendMailForgotPass(utilisateur, utilisateurDTO.getNewPass());
    }

    @Override
    public String findNomComplet(Long id) {
        Utilisateur utilisateur = utilisateurrRepository.findUtilisateurById(id);
        if(utilisateur == null)
            return "";
        return utilisateur.getPrenom() + " " +utilisateur.getNom();
    }
}
