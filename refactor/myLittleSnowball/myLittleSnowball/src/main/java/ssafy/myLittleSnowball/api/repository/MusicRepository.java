package ssafy.myLittleSnowball.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.myLittleSnowball.domain.entity.Music;

@Repository
@Transactional(readOnly = true)
public interface MusicRepository extends JpaRepository<Music, Long> {
}
