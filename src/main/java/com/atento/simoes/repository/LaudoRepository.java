package com.atento.simoes.repository;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atento.simoes.entity.Laudo;

public interface LaudoRepository extends JpaRepository<Laudo, Long> {

	Object saveAndFlush(@Valid Optional<Laudo> laudo);
}