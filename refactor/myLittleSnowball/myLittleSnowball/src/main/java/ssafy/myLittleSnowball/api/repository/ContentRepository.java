package ssafy.myLittleSnowball.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.myLittleSnowball.domain.entity.Board;
import ssafy.myLittleSnowball.domain.entity.Content;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ContentRepository extends JpaRepository<Content, Long> {

    // 글 작성
    @Transactional
    @Override
    <S extends Content> S save(S content);


    // 글 삭제
    @Transactional
    @Override
    void delete(Content content);

    // 글 수정
    @Transactional
    @Modifying
    @Query(value = "UPDATE Content c SET c.content = :content, c.image_url = :image_url WHERE c.content_id = :content_id", nativeQuery = true)
    void updateContent(@Param("content") String content, @Param("image_url") String imageUrl, @Param("content_id") Long contentId);

    List<Content> findByBoard(Board board);
}
