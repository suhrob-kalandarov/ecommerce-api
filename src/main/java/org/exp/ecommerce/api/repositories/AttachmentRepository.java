package org.exp.ecommerce.api.repositories;

import org.exp.ecommerce.api.models.base.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
