package com.ssg.webpos.repository;

import com.ssg.webpos.domain.User;
import com.ssg.webpos.dto.PhoneNumberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Transactional
class UserRepositoryTest {
  @Autowired
  UserRepository userRepository;

  @Transactional
  @Test
  void 전화번호일치() {
    PhoneNumberDTO phoneNumberDto = new PhoneNumberDTO();
    phoneNumberDto.setPhoneNumber("01022223333");

    // when
    Optional<User> findUser = userRepository.findByPhoneNumber(phoneNumberDto.getPhoneNumber());
    System.out.println("findUser = " + findUser);

    // then
    assertTrue(findUser.isPresent());
    assertThat(findUser.get().getName()).isEqualTo("고경환"); // 회원 이름이 일치하는지 확인
  }

  @Test
  void 전화번호불일치() {
    //given
    PhoneNumberDTO phoneNumberDto = new PhoneNumberDTO();
    phoneNumberDto.setPhoneNumber("01011112226");

    // when
    Optional<User> findUser = userRepository.findByPhoneNumber(phoneNumberDto.getPhoneNumber());
    System.out.println("findUser = " + findUser);

    // then
    assertTrue(findUser.isEmpty());// findUser가 비어있어야함
  }
}