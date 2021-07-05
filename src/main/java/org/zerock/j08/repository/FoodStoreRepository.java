package org.zerock.j08.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j08.entity.FoodStore;


public interface FoodStoreRepository extends JpaRepository<FoodStore, Long> {

    @EntityGraph(attributePaths = "foodMenus", type = EntityGraph.EntityGraphType.FETCH)
    @Query("select s from FoodStore s where s.fno = :sno ")
    FoodStore getById(Long sno);

//    @EntityGraph(attributePaths = "storeImages",
//            type = EntityGraph.EntityGraphType.FETCH)
    @Query("select s.fno, s.fname, si.uuid, si.fileName " +
            "from FoodStore s left join s.storeImages si " +
            "where si.main = true and s.fno > 0 group by s ")
    Page<Object[]> getList(Pageable pageable);

}
