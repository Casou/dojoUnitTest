package com.bparent.dojo.unitTest.repository;

import com.bparent.dojo.unitTest.bean.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByAgeGreaterThan(Integer age);

}