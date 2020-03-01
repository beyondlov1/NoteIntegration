package com.beyond.note.integration.repository;

import com.beyond.note.integration.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author beyond
 */
public interface AttachmentRepository extends JpaRepository<Attachment,String> {
}
