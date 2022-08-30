package com.zt.mybatisplusexample;

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
                .eq(CategoryEntity::getName, "家电")
                ;
        List<CategoryEntity> categoryEntityList = mpCategoryRepository.list(categoryQueryWrapper);
        CategoryEntity categoryEntity = categoryEntityList.get(0);

        GoodsEntity goodsEntity = new GoodsEntity()
                .setName("平板电脑")
                .setPrice(new BigDecimal("4699.00"))
                .setStockNum(10000L)
                .setFlag(GoodsFlagEnum.UP)
                .setCategoryId(categoryEntity.getId())
                .setCreateBy("admin")
                ;
        mpGoodsRepository.save(goodsEntity);
    }

    @Test
    public void testQuery() {
        List<GoodsEntity> goodsEntityList = mpGoodsRepository.list();
        for (GoodsEntity goodsEntity : goodsEntityList) {
            log.info(goodsEntity.toString());
            log.info(goodsEntity.getFlag().getValue() + "");
        }
    }

    @Test
    public void testAddCategory(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("家电");
        mpCategoryRepository.save(categoryEntity);
    }

    @Test
    public void testUpdate() {
        LambdaQueryWrapper<GoodsEntity> goodsQueryWrapper = new LambdaQueryWrapper<GoodsEntity>().eq(GoodsEntity::getName, "平板电脑");
        List<GoodsEntity> goodsEntityList = mpGoodsRepository.list(goodsQueryWrapper);
        GoodsEntity goodsEntity = goodsEntityList.get(0);
        goodsEntity.setPrice(new BigDecimal("6499.00"));
        mpGoodsRepository.updateById(goodsEntity);
    }

    @Test
    void testQueryTime() {
        LambdaQueryWrapper<GoodsEntity> queryWrapper = new LambdaQueryWrapper<GoodsEntity>()
                .lt(GoodsEntity::getCreateTime, LocalDateTime.parse("2021-04-08 10:00:00")) // where create_time < 2021-04-08 10:00:00
                .gt(GoodsEntity::getCreateTime, LocalDateTime.parse("2021-04-09 10:00:00")) // where create_time > 2021-04-09 10:00:00
                ;
        List<GoodsEntity> list = mpGoodsRepository.list(queryWrapper);
    }

}
