package ssafy.myLittleSnowball.api.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.myLittleSnowball.domain.entity.Snowball;

import javax.persistence.EntityManager;

@Repository
@Transactional(readOnly = true)
public interface SnowballRepository extends JpaRepository<Snowball, Long> {

    // 스노우볼 생성
    @Override
    <S extends Snowball> S save(S snowball);
    // 스노우볼 삭제
    @Override
    void delete(Snowball snowball);
}
