package com.projeto_engenharia.projeto.utils;

import com.projeto_engenharia.projeto.enums.Role;

public class Utils {
    public static Role parseRole(String role) {
        String roleWithPrefix = "ROLE_"+role.toUpperCase();
        return Role.valueOf(roleWithPrefix);
    }

}
