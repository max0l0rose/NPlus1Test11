package com.example.nplus1test2.repository;

import com.example.nplus1test2.dto.IDto1;
import com.example.nplus1test2.model.Beach;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BeachRepository extends JpaRepository<Beach, Long> {

    @EntityGraph(value = "Beach.lifeguards"
            //, type = EntityGraph.EntityGraphType.LOAD // LOAD > FETCH
    )
    List<Beach> findByNameStartingWith(String s);

    List<Beach> queryByNameStartingWith(String s); // no graph


    @Query(value = "\n SELECT b \n"
                           + "FROM Beach b \n"
                           + "LEFT JOIN FETCH b.lifeguards l \n"
                           //+ "GROUP BY b.id"
    )
    List<Beach> simple(String s);


    //    @Query(value = "\nSELECT b.id as id, b.name as name, size (b.lifeguards) as lCount \n" +
//                           "FROM Beach b \n" +
//                           "LEFT JOIN b.lifeguards l \n" +
//                           "GROUP BY b.id")
    @Query(value = "\nSELECT b.id as id, b.name as name, size(b.lifeguards) as LCount \n" +
                           "FROM Beach b \n" +
                           "LEFT JOIN b.lifeguards l \n" +
                           "GROUP BY b.id")
    List<IDto1> beachesAndLifeguardsCount();


    @Query(value = "\nSELECT b.id, b.name as name, COUNT(beach_id) as LCount \n" +
                           "FROM beach b \n" +
                           "LEFT JOIN lifeguard l ON b.id = l.beach_id \n" +
                           "GROUP BY l.beach_id, b.name, b.id",
            nativeQuery = true)
    List<IDto1> beachesAndLifeguardsCountNative();


//    //TODO Correct the query
//    //Get a list of the names of the beaches where the number of lifeguards does not exceed N people
//    @Query(value = "SELECT b.name FROM beach b\n" +
//                           "LEFT JOIN lifeguard l on b.id = l.beach_id\n" +
//                           "GROUP BY b.id\n" +
//                           "having count(l.id) <= ? AND count(l.id)>0", nativeQuery = true)
//    List<String> findAllBeachesWithLifeguardsLessThan(Integer n);
//
//    //TODO Correct the query
//    //Get a list of beach names with the maximum overall speed of lifeguards
//    //For example overall speed of Malibu beach is 14
//    @Query(value = "SELECT b.name from beach b, (\n" +
//                           "    SELECT l2.beach_id, sum(speed), ms\n" +
//                           "    FROM lifeguard l2, (\n" +
//                           "        SELECT max(ss) as ms\n" +
//                           "        FROM (\n" +
//                           "                 SELECT sum(l.speed) as ss\n" +
//                           "                 FROM lifeguard l\n" +
//                           "                 GROUP BY l.beach_id\n" +
//                           "             ) as z\n" +
//                           "    ) as q\n" +
//                           "    GROUP BY beach_id, ms\n" +
//                           "    HAVING sum(speed) = ms\n" +
//                           ") as q2\n" +
//                           "WHERE b.id = q2.beach_id", nativeQuery = true)
//    List<String> findAllByMaxTotalSpeed();
}
