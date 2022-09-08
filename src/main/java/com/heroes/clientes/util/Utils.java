package com.heroes.clientes.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Utils {
    public static Boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    private static String dv(Integer rut) {
        Integer M=0;
        Integer S=1;
        Integer T=rut;
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }

    public static Boolean dvValidation(Integer rut, String dv){
        return dv(rut).equalsIgnoreCase(dv);
    }
}
