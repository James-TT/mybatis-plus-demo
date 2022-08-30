package com.zt.mybatisplusexample.repository.impl;

import com.zt.mybatisplusexample.entity.CategoryEntity;
import com.zt.mybatisplusexample.mapper.ICategoryMapper;
import com.zt.mybatisplusexample.repository.MPCategoryRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * @author zt
 * @since 2022-08-30
 */
@Service
public class CategoryRepositoryImpl extends ServiceImpl<ICategoryMapper, CategoryEntity> implements MPCategoryRepository {

}
