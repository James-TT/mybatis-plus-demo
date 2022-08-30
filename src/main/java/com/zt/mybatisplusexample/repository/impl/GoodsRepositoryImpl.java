package com.zt.mybatisplusexample.repository.impl;

import com.zt.mybatisplusexample.entity.GoodsEntity;
import com.zt.mybatisplusexample.mapper.IGoodsMapper;
import com.zt.mybatisplusexample.repository.MPGoodsRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * @author zt
 * @since 2022-08-30
 */
@Service
public class GoodsRepositoryImpl extends ServiceImpl<IGoodsMapper, GoodsEntity> implements MPGoodsRepository {

}
