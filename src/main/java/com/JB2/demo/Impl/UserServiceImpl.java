package com.JB2.demo.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JB2.demo.Helper.Appconstants;
import com.JB2.demo.Helper.ResourceNotFoundException;
import com.JB2.demo.Repository.UserRepo;
import com.JB2.demo.ServiceInterface.UserService;
import com.JB2.demo.entity.User;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));   
    //set user Roles:
    user.setRoleList(List.of(Appconstants.ROLE_USER));
    return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
      return  userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }




    

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(
            ()->new ResourceNotFoundException("jhh")
        );
        userRepo.delete(user);
    }


   @Override
    public boolean isUserExist(Long userId) {
 User user2 = userRepo.findById(userId).orElse(null);
return user2!=null?true:false;
}

    @Override
    public boolean isUserExistByEmail(String email) {
         User user2 = userRepo.findByEmail(email).orElse(null);
         return user2!=null?true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}
