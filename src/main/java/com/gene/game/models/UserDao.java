/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gene.game.models;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Gene
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    
    public User findByEmail(String email);
}
