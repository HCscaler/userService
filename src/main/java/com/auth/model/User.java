package com.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String username;
    
    @Column(nullable = false)
<<<<<<< HEAD
    private String password;
    
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String gmail;
=======
    @NotNull
    private String fullname;
>>>>>>> e589004bbb378327118393fe497fceebd20dd5f6

//    @JsonIgnore // This will prevent the password from being serialized
    @Column(nullable = false)
    @NotNull
    private String password;
    
    @Email(message = "Email should be valid")
    @NotNull
    @NotEmpty
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

<<<<<<< HEAD
    public User(Long id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    

    public User(Long id, String username, String password, String name, String gmail, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gmail = gmail;
		this.roles = roles;
	}
    
	public Long getId() {
        return id;
    }
=======
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
>>>>>>> e589004bbb378327118393fe497fceebd20dd5f6

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

<<<<<<< HEAD
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
=======
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(Long id, @NotNull String username, @NotNull String fullname, @NotNull String password,
			@Email(message = "Email should be valid") @NotNull @NotEmpty String email, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}

>>>>>>> e589004bbb378327118393fe497fceebd20dd5f6
    
}
