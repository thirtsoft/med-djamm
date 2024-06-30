package com.meddjamm.sn.config.event;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.utils.ConstantDeployment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private Utilisateur utilisateur;
    private String url;
    private String defaultPassword;

    public RegistrationCompleteEvent(Utilisateur utilisateur, String defaultPassword) {
        super(utilisateur);
        this.utilisateur = utilisateur;
        this.url = ConstantDeployment.HOST_API;
        this.defaultPassword = defaultPassword;
    }
}