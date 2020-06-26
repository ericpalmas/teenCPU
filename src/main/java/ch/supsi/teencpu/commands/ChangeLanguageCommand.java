package ch.supsi.teencpu.commands;

import ch.supsi.teencpu.services.LanguageService;

public class ChangeLanguageCommand implements Command {

    private LanguageService languageService;
    private String languageToSet;

    public ChangeLanguageCommand(LanguageService languageService) {
        this.languageService = languageService;
    }

    public void setLanguageToSet(String languageToSet){
        this.languageToSet = languageToSet;
    }

    @Override
    public void execute() {
        languageService.changeLanguage(languageToSet);
    }

}
