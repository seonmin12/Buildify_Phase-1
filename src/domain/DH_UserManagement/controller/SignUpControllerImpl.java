package domain.DH_UserManagement.controller;

import common.ValidCheck;
import domain.DH_UserManagement.service.SignUpService;

public class SignUpControllerImpl {
    private final ValidCheck validCheck;
    private final SignUpService signUpService;

    public SignUpControllerImpl(ValidCheck validCheck, SignUpService signUpService) {
        this.validCheck = validCheck;
        this.signUpService = signUpService;
    }
}
