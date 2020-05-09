package co.lq.modules.shop.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.util.StrUtil;
import co.lq.aop.log.Log;
import co.lq.exception.BadRequestException;
import co.lq.modules.shop.domain.Category;
import co.lq.modules.shop.service.CategoryService;
import co.lq.modules.shop.service.dto.CategoryDTO;
import co.lq.modules.shop.service.dto.CategoryQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author billy
 * @date 2019-10-03
 */
@Api(tags = "店铺:商品分类管理")
@RestController
@RequestMapping("api")
public class CategoryController {

    private final CategoryService CategoryService;

    public CategoryController(CategoryService storeCategoryService) {
        this.CategoryService = storeCategoryService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/category/download")
    @PreAuthorize("@el.check('admin','cate:list')")
    public void download(HttpServletResponse response, CategoryQueryCriteria criteria) throws IOException {
        CategoryService.download(CategoryService.queryAll(criteria), response);
    }

    @Log("查询商品分类")
    @ApiOperation(value = "查询商品分类")
    @GetMapping(value = "/category")
    @PreAuthorize("@el.check('admin','STORECATEGORY_ALL','STORECATEGORY_SELECT')")
    public ResponseEntity<Object> getStoreCategorys(CategoryQueryCriteria criteria) {
        List<CategoryDTO> categoryDTOList = CategoryService.queryAll(criteria);
        return new ResponseEntity<>(CategoryService.buildTree(categoryDTOList), HttpStatus.OK);
    }

    @Log("新增商品分类")
    @ApiOperation(value = "新增商品分类")
    @PostMapping(value = "/category")
    @PreAuthorize("@el.check('admin','STORECATEGORY_ALL','STORECATEGORY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Category resources) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        if (resources.getPid() > 0 && StrUtil.isBlank(resources.getPic())) {
            throw new BadRequestException("子分类图片必传");
        }
        CategoryDTO categoryDTO = CategoryService.create(resources);
        return new ResponseEntity(categoryDTO, HttpStatus.CREATED);
    }

    @Log("修改商品分类")
    @ApiOperation(value = "修改商品分类")
    @PutMapping(value = "/category")
    @PreAuthorize("@el.check('admin','STORECATEGORY_ALL','STORECATEGORY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Category resources) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        if (resources.getPid() > 0 && StrUtil.isBlank(resources.getPic())) {
            throw new BadRequestException("子分类图片必传");
        }
        CategoryService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除商品分类")
    @ApiOperation(value = "删除商品分类")
    @DeleteMapping(value = "/category/{id}")
    @PreAuthorize("@el.check('admin','STORECATEGORY_ALL','STORECATEGORY_DELETE')")
    public ResponseEntity delete(@PathVariable String id) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        CategoryService.delete(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
