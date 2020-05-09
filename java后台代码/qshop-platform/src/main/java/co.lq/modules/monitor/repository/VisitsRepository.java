package co.lq.modules.monitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.lq.modules.monitor.domain.Visits;

/**
 * @author billy
 * @date 2018-12-13
 */
@Repository
public interface VisitsRepository extends JpaRepository<Visits, Long> {

    /**
     * findByDate
     *
     * @param date 日期
     * @return Visits
     */
    Visits findByDate(String date);

    /**
     * 获得一个时间段的记录
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return List
     */
    @Query(value = "select * FROM visits where create_time between ?1 and ?2", nativeQuery = true)
    List<Visits> findAllVisits(String date1, String date2);
}
