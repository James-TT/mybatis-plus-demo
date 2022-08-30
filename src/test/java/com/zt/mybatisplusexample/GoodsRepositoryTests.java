package com.zt.mybatisplusexample;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zt.mybatisplusexample.entity.CategoryEntity;
import com.zt.mybatisplusexample.entity.GoodsEntity;
import com.zt.mybatisplusexample.entity.enums.GoodsFlagEnum;
import com.zt.mybatisplusexample.repository.MPCategoryRepository;
import com.zt.mybatisplusexample.repository.MPGoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author zt
 * @since 2022-08-30
 */
@Component
@Slf4j
public class GoodsRepositoryTests extends MybatisPlusExampleApplicationTests {

    @Autowired
    private MPCategoryRepository mpCategoryRepository;

    @Autowired
    private MPGoodsRepository mpGoodsRepository;

    @Test
    public void testAdd() {
        LambdaQueryWrapper<CategoryEntity> categoryQueryWrapper = new LambdaQueryWrapper<CategoryEntity>()
                .eq(CategoryEntity::getName, "家电");
        List<CategoryEntity> categoryEntityList = mpCategoryRepository.list(categoryQueryWrapper);
        CategoryEntity categoryEntity = categoryEntityList.get(0);

        GoodsEntity goodsEntity = new GoodsEntity()
                .setName("平板电脑")
                .setPrice(new BigDecimal("4699.00"))
                .setStockNum(10000L)
                .setFlag(GoodsFlagEnum.UP)
                .setCategoryId(categoryEntity.getId())
                .setCreateBy("admin");
        mpGoodsRepository.save(goodsEntity);
    }

    @Test
    public void testQuery() {

        List<GoodsEntity> goodsEntityList = mpGoodsRepository.list();
        for (GoodsEntity goodsEntity : goodsEntityList) {
            System.out.println(goodsEntity.toString());
            //枚举展示
            System.out.println(goodsEntity.getFlag().getDesc());
        }

        //查询一条数据, 如果存在多条会报错
        Wrapper<GoodsEntity> wrapper = new LambdaQueryWrapper<GoodsEntity>().eq(GoodsEntity::getName, "手机");
        GoodsEntity goodsEntity = mpGoodsRepository.getOne(wrapper);
        System.out.println(goodsEntity.toString());

        //范围查询
        LambdaQueryWrapper<GoodsEntity> queryWrapper = new LambdaQueryWrapper<GoodsEntity>()
                .lt(GoodsEntity::getCreateTime, LocalDateTime.parse("2023-04-08 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .gt(GoodsEntity::getCreateTime, LocalDateTime.parse("2022-04-09 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        List<GoodsEntity> list = mpGoodsRepository.list(queryWrapper);
        System.out.println("范围查询的数量:" + list.size());
        list.forEach(goodsEntity1 -> goodsEntity1.toString());

        //根据id 查询
        GoodsEntity byId = mpGoodsRepository.getById(3);
        System.out.println(byId.toString());

        //带条件查询
        Wrapper<GoodsEntity> lambdaQueryWrapper = new LambdaQueryWrapper<GoodsEntity>().eq(GoodsEntity::getFlag, 0)
                .gt(GoodsEntity::getStockNum, 500);
        List<GoodsEntity> list1 = mpGoodsRepository.list(lambdaQueryWrapper);
        System.out.println("带条件查询的数量:" + list1.size());
        list1.forEach(goodsEntity1 -> goodsEntity1.toString());

        //带条件查询某些字段
       /* Wrapper<GoodsEntity> queryWrapper1 = new LambdaQueryWrapper<GoodsEntity>().select("id","name").eq(GoodsEntity::getFlag, 0)
                .gt(GoodsEntity::getStockNum, 500);*/
    }

    @Test
    public void testAddCategory() {
        //单个插入
     /*   CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("家电");
        mpCategoryRepository.save(categoryEntity);*/

        //批量插入
      /*  List<CategoryEntity> categoryEntities = Arrays.asList(
                new CategoryEntity("肉类"),
                new CategoryEntity("蔬菜"),
                new CategoryEntity("家具"));
        mpCategoryRepository.saveBatch(categoryEntities);*/

        //根据主键，保存或更新
      /*  CategoryEntity categoryEntity = new CategoryEntity(1L, "家电1");
        mpCategoryRepository.saveOrUpdate(categoryEntity);*/

        //根据主键，批量保存或更新
        List<CategoryEntity> categoryEntities = Arrays.asList(
                new CategoryEntity(1L, "家电2"),
                new CategoryEntity(5L, "花卉"));
        mpCategoryRepository.saveOrUpdateBatch(categoryEntities);
    }

    @Test
    public void testUpdate() {
        LambdaQueryWrapper<GoodsEntity> goodsQueryWrapper = new LambdaQueryWrapper<GoodsEntity>().eq(GoodsEntity::getName, "平板电脑");
        List<GoodsEntity> goodsEntityList = mpGoodsRepository.list(goodsQueryWrapper);
        GoodsEntity goodsEntity = goodsEntityList.get(0);
        goodsEntity.setPrice(new BigDecimal("6499.00"));
        mpGoodsRepository.updateById(goodsEntity);
    }


}
