package com.hgp.controladordeveiculos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgp.controladordeveiculos.dto.UserDTO;
import com.hgp.controladordeveiculos.entities.User;
import com.hgp.controladordeveiculos.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		List<UserDTO> list = repository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return list;
	}
	
	@Transactional
	public UserDTO insertUser(UserDTO dto) {
		User entity = new User();
		this.dtoToUser(entity, dto);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	private void dtoToUser(User entity, UserDTO dto) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setBirthDate(dto.getBirthDate());
	}
}
