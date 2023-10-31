package com.projeto_engenharia.projeto.enums;

public enum UsuarioStatus {
    ACTIVE(true, "active"),
    INACTIVE(false, "inactive");

    private final Boolean value;
    private final String description;

    UsuarioStatus(Boolean value, String description){
        this.value = value;
        this.description = description;
    }

    public Boolean getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static UsuarioStatus fromDescription(String description) {
        for (UsuarioStatus status : UsuarioStatus.values()){
            if(status.getDescription().equalsIgnoreCase(description)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: "+ description);
    }

    public static UsuarioStatus fromValue(Boolean statusValue){
        for(UsuarioStatus status : UsuarioStatus.values()){
            if(status.getValue() == statusValue){
                return status;
            }
        }
        throw new IllegalArgumentException("O valor não corresponde a nenhum status: "+ statusValue);
    }
}
