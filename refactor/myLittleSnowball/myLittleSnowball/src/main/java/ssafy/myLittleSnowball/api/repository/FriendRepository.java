package ssafy.myLittleSnowball.api.repository;

import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.myLittleSnowball.domain.entity.Friend;

@Repository
@Transactional(readOnly = true)
public interface FriendRepository extends JpaRepository<Friend, Long> {

    // 친구 요청

    @Override
    <S extends Friend> S save(S friend);

    // 요청 승낙
    @Transactional
    @Modifying
    @Query(value = "UPDATE Friend f SET f.is_accepted = true where f.follower_id = :follower_id and f.followee_id = :followee_id", nativeQuery = true)
    void acceptRequest(@Param("follower_id") Long followerId, @Param("followee_id") Long followeeId);

    // 친구 취소
    @Transactional
    @Modifying
    @Query(value = "DELETE Friend f WHERE f.follower_id = :follower_id and f.followee_id = :followee_id", nativeQuery = true)
    void deleteByFollowerIdAndFolloweeId(@Param("follower_id") Long followerId,@Param("followee_id") Long followeeId);

    // 친구 검색



}
