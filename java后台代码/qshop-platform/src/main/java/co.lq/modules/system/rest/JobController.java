package co.lq.modules.system.rest;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.lq.aop.log.Log;
import co.lq.config.DataScope;
import co.lq.exception.BadRequestException;
import co.lq.modules.system.domain.PlatformJob;
import co.lq.modules.system.service.PlatformJobService;
import co.lq.modules.system.service.dto.PlatformJobQueryCriteria;
import co.lq.utils.ThrowableUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author billy
 * @date 2019-03-29
 */
@Api(tags = "系统：岗位管理")
@RestController
@RequestMapping("/api/job")
public class JobController {

    private final PlatformJobService platformJobService;

    private final DataScope          dataScope;

    private static final String      ENTITY_NAME = "platformJob";

    public JobController(PlatformJobService platformJobService, DataScope dataScope) {
        this.platformJobService = platformJobService;
        this.dataScope = dataScope;
    }

    @Log("导出岗位数据")
    @ApiOperation("导出岗位数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','job:list')")
    public void download(HttpServletResponse response, PlatformJobQueryCriteria criteria) throws IOException {
        platformJobService.download(platformJobService.queryAll(criteria), response);
    }

    @Log("查询岗位")
    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("@el.check('admin','job:list','user:list')")
    public ResponseEntity<Object> getJobs(PlatformJobQueryCriteria criteria, Pageable pageable) {
        // 数据权限
        criteria.setDeptIds(dataScope.getDeptIds());
        return new ResponseEntity<>(platformJobService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增岗位")
    @ApiOperation("新增岗位")
    @PostMapping
    @PreAuthorize("@el.check('admin','job:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody PlatformJob resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        return new ResponseEntity<>(platformJobService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改岗位")
    @ApiOperation("修改岗位")
    @PutMapping
    @PreAuthorize("@el.check('admin','job:edit')")
    public ResponseEntity<Object> update(@Validated(PlatformJob.Update.class) @RequestBody PlatformJob resources) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        platformJobService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除岗位")
    @ApiOperation("删除岗位")
    @DeleteMapping
    @PreAuthorize("@el.check('admin','job:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        try {
            platformJobService.delete(ids);
        } catch (Throwable e) {
            ThrowableUtil.throwForeignKeyException(e, "所选岗位存在用户关联，请取消关联后再试");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
