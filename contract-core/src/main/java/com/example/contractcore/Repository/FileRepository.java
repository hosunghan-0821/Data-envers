package com.example.contractcore.Repository;

import com.example.contractcore.Domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
