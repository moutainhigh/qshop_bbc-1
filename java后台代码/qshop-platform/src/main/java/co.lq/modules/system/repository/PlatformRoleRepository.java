package co.lq.modules.system.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.lq.modules.system.domain.PlatformRole;

/**
 * @author billy
 * @date 2018-12-03
 */
@SuppressWarnings("all")
public interface PlatformRoleRepository
        extends JpaRepository<PlatformRole, Long>, JpaSpecificationExecutor<PlatformRole> {

    /**
     * 根据名称查询
     *
     * @param name /
     * @return /
     */
    PlatformRole findByName(String name);

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return
     */
    Set<PlatformRole> findByUsers_Id(Long id);

    /**
     * 解绑角色菜单
     *
     * @param id 菜单ID
     */
    @Modifying
    @Query(value = "delete from roles_menus where menu_id = ?1", nativeQuery = true)
    void untiedMenu(Long id);

    /**
     * 根据角色权限查询
     *
     * @param permission /
     * @return /
     */
    PlatformRole findByPermission(String permission);
}
