package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenDao extends BaseRepository<Token> {

    Optional<Token> findByValue(String value);

    boolean existsByValue(String value);

}
