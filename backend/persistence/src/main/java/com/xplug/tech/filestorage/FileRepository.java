package com.xplug.tech.filestorage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface FileRepository extends JpaRepository<File, String> {

    Optional<File> findById(String id);

}
