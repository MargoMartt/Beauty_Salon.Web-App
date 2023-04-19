package com.example.beauty_salon.dao;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BeautyMastersRepository extends JpaRepository<BeautyMastersEntity, Integer> {

}
