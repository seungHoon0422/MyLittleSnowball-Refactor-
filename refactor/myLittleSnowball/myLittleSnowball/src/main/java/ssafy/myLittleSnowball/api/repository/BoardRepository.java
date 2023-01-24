package ssafy.myLittleSnowball.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.myLittleSnowball.domain.entity.Board;

@Repository
@Transactional(readOnly = true)
public interface BoardRepository extends JpaRepository<Board, Long> {
}
