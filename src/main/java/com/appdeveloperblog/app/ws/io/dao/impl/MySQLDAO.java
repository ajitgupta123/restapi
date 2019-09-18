/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.io.dao.impl;

import com.appdeveloperblog.app.ws.io.dao.DAO;
import com.appdeveloperblog.app.ws.io.entity.UserEntity;
import com.appdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appdeveloperblog.app.ws.utils.HibernateUtils;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author gts
 */
public class MySQLDAO implements DAO{
    
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }
    
    @Override
    public UserDTO getUser(String id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("userId"), id));

        // Fetch single result
        UserEntity userEntity = session.createQuery(criteria).getSingleResult();
        
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDto);
        
        return userDto;
    }

    @Override
    public UserDTO getUserByUserName(String userName) {

        UserDTO userDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("email"), userName));

        // Fetch single result
        Query<UserEntity> query = session.createQuery(criteria);
        List<UserEntity> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            UserEntity userEntity = resultList.get(0);
            userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
        }
   

        return userDto;
    }
    
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDTO retObj = null;
        
        UserEntity userEntity = new UserEntity();
        
        BeanUtils.copyProperties(userDTO, userEntity );
        
        if (session != null) {
            session.beginTransaction();
            session.save(userEntity);
            session.getTransaction().commit();
        }
        
        retObj = new UserDTO();
        BeanUtils.copyProperties(userEntity, retObj);
        
        return retObj;
    }

    @Override
    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }
    
    @Override
    public void updateUser(UserDTO userProfile) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userProfile, userEntity);

        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
        
    }
    
}
