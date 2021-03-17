package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    @PersistenceContext
    private final EntityManager em;
    /**
     * DB에 저장
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }
    /**
     * 하나 찾기
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    /**
     * 전체 리스트로 반환
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
    /**
     *
     */

}
