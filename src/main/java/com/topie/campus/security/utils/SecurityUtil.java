package com.topie.campus.security.utils;

import com.topie.campus.security.security.OrangeSecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgj on 2015/10/26.
 */
public class SecurityUtil {

    public static String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof OrangeSecurityUser) userName = ((OrangeSecurityUser) principal).getUsername();
        return userName;
    }

    public static Integer getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = null;
        if (principal instanceof OrangeSecurityUser) userId = ((OrangeSecurityUser) principal).getId();
        return userId;
    }

    public static List<Integer> getCurrentRoles() {
        List<Integer> list = new ArrayList<>();
        OrangeSecurityUser orangeSecurityUser = getCurrentSecurityUser();
        if (orangeSecurityUser == null) return list;
        for (GrantedAuthority grantedAuthority : orangeSecurityUser.getAuthorities()) {
            list.add(Integer.valueOf(grantedAuthority.getAuthority()));
        }
        return list;
    }

    public static OrangeSecurityUser getCurrentSecurityUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof OrangeSecurityUser) return (OrangeSecurityUser) principal;
        return null;
    }

    public static String encodeString(String character) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(character);
    }

    public static boolean matchString(String character, String encodedCharacter) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(character, encodedCharacter);
    }

}
