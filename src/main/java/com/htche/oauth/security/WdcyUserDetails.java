package com.htche.oauth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.htche.oauth.entity.Privilege;
import com.htche.oauth.entity.Users;

/**
 * @author Shengzhao Li
 */
public class WdcyUserDetails implements UserDetails {

    private static final long serialVersionUID = 3957586021470480642L;

    protected static final String ROLE_PREFIX = "ROLE_";
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name());

    protected Users user;

    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public WdcyUserDetails() {
    }

    public WdcyUserDetails(Users user) {
        this.user = user;
        initialAuthorities();
    }

    private void initialAuthorities() {
        //Default, everyone have it
        this.grantedAuthorities.add(DEFAULT_USER_ROLE);
        //default user have all privileges
        if (user.defaultUser()) {
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.UNITY.name()));
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.MOBILE.name()));
        } else {
            final List<Privilege> privileges = user.privileges();
            for (Privilege privilege : privileges) {
                this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege.name()));
            }
        }
    }

    /**
     * Return authorities, more information see {@link #initialAuthorities()}
     *
     * @return Collection of GrantedAuthority
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Users user() {
        return user;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}