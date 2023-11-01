package com.projeto_engenharia.projeto.enums;

public enum UserStatus {
    ACTIVE(true, "active"),
    INACTIVE(false, "inactive");

    private final Boolean value;
    private final String description;

    UserStatus(Boolean value, String description){
        this.value = value;
        this.description = description;
    }

    public Boolean getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static UserStatus fromDescription(String description) {
        for (UserStatus status : UserStatus.values()){
            if(status.getDescription().equalsIgnoreCase(description)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: "+ description);
    }

    public static UserStatus fromValue(Boolean statusValue){
        for(UserStatus status : UserStatus.values()){
            if(status.getValue() == statusValue){
                return status;
            }
        }
        throw new IllegalArgumentException("O valor não corresponde a nenhum status: "+ statusValue);
    }
}
