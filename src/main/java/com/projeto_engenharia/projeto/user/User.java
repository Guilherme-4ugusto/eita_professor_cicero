package com.projeto_engenharia.projeto.user;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projeto_engenharia.projeto.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Table(name = "users", schema = "plataforma_cursos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class User implements UserDetails{

	@Id
	@SequenceGenerator(name = "users_seq", sequenceName = "users_sequence", initialValue = 100000, allocationSize = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "users_seq")
	private Long id;
	@Column(name = "email", unique = true, length = 100)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
	@Column(name = "is_active", columnDefinition = "boolean default false")
    private Boolean isActive;

    public User(UserRequestDTO data) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	this.email = data.email();
    	this.password = passwordEncoder.encode(data.password());    	
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Boolean userExists(Long userId){
		
		return false;
	}
}
